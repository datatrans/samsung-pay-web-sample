package ch.datatrans.samsungpay.client;
/**
 * Created by fabrizio.grillone@datatrans.ch
 */
import ch.datatrans.samsungpay.domain.*;
import ch.datatrans.samsungpay.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public class SamsungPayClient {

    @Value("${samsung.serviceUrl}")
    private String serviceUrl = null;

    @Value("${callbackBaseUrl}")
    private String callbackBaseUrl = null;

    @Value("${samsung.serviceId}")
    private String serviceId = null;

    @Value("${merchant.name}")
    private String merchantName = null;

    @Value("${merchant.reference}")
    private String merchantReference = null;

    private RestTemplate restTemplate = null;

    @Autowired
    public SamsungPayClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public void notifyTransaction(String referenceId){
        NotificationRequest nr = new NotificationRequest();
        Payment p = new Payment();

        Merchant merchant = new Merchant();
        merchant.setName(merchantName);
        merchant.setUrl(callbackBaseUrl);
        merchant.setReference(merchantReference);
        p.setMerchant(merchant);

        p.setProvider("DATATRANS");
        p.setReference(referenceId);

        Service service = new Service();
        service.setId(serviceId);
        p.setService(service);

        p.setStatus(Constants.Status.CHARGED.name());

        nr.setPayment(p);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        nr.setTimestamp(timestamp.getTime());

        HttpEntity entity = new HttpEntity(nr, getRequestHeaders());
        restTemplate
                .exchange(serviceUrl +"/ops/v1/notifications", HttpMethod.POST,entity, NotificationRequest.class);

    }

    public TransactionPaymentCredentialResult getPaymentCredentials(String uuid, String referenceId){
        HttpEntity entity = new HttpEntity(getRequestHeaders());
        ResponseEntity<TransactionPaymentCredentialResult> response = restTemplate
                .exchange(serviceUrl +"/ops/v1/transactions/paymentCredentials/"+referenceId +"?serviceId="+serviceId, HttpMethod.GET,entity, TransactionPaymentCredentialResult.class);

        return response.getBody();
    }

    public TransactionCreateResult createTransaction(String amount,String currency, UUID sessionId){
        TransactionCreateRequest tcr = new TransactionCreateRequest();

        tcr.setCallback(callbackBaseUrl +"/callback/"+ sessionId.toString());

        PaymentDetails pd = new PaymentDetails();
        Amount am = new Amount();
        am.setCurrency(Constants.Currency.valueOf(currency).name());
        am.setTotal(Float.parseFloat(amount));
        am.setOption("FORMAT_TOTAL_ESTIMATED_AMOUNT");

        pd.setAmount(am);
        pd.setBrands(new String[]{Constants.CreditCardBrands.MC.name(),Constants.CreditCardBrands.VIS.name()});

        Merchant merchant = new Merchant();
        merchant.setName(merchantName);
        merchant.setUrl(callbackBaseUrl);
        merchant.setReference(merchantReference);

        pd.setMerchant(merchant);

        pd.setOrderNumber(merchantReference + System.currentTimeMillis());

        Protocol protocol = new Protocol();
        protocol.setType("3DS");
        protocol.setVersion("80");
        pd.setProtocol(protocol);

        pd.setRecurring(false);

        Service service = new Service();
        service.setId(serviceId);

        pd.setService(service);

        tcr.setPaymentDetails(pd);

        HttpEntity<TransactionCreateRequest> entity = new HttpEntity<>(tcr, getRequestHeaders());
        ResponseEntity<TransactionCreateResult> response = restTemplate
                .exchange(serviceUrl +"/ops/v1/transactions", HttpMethod.POST, entity, TransactionCreateResult.class);

        return response.getBody();
    }

    private HttpHeaders getRequestHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Request-Id", UUID.randomUUID().toString());

        return headers;
    }
}
