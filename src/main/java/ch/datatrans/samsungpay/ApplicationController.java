package ch.datatrans.samsungpay;

/**
 * Created by fabrizio.grillone@datatrans.ch
 */

import ch.datatrans.samsungpay.client.DatatransClient;
import ch.datatrans.samsungpay.client.SamsungPayClient;
import ch.datatrans.samsungpay.domain.DatatransTransaction;
import ch.datatrans.samsungpay.domain.TransactionCreateResult;
import ch.datatrans.samsungpay.domain.TransactionPaymentCredentialResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@Controller
public class ApplicationController {

    @Value("${callbackBaseUrl}")
    private String callbackBaseUrl = null;

    @Value("${samsung.serviceId}")
    private String serviceId = null;

    @RequestMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("callbackBaseUrl", callbackBaseUrl);
        model.addAttribute("serviceId",serviceId);
        return "index";
    }

    private SamsungPayClient samsungPayClient = null;
    private DatatransClient datatransClient = null;

    @Autowired
    public ApplicationController(SamsungPayClient samsungPayClient, DatatransClient datatransClient){
        this.samsungPayClient = samsungPayClient;
        this.datatransClient = datatransClient;
    }

    private HashMap<String, String> credentialResultHashMap = new HashMap<String,String>();

    //get payment credentials as soon there is a callback from samsung
    @RequestMapping(value = "/callback/{uuid}")
    public String callback(@PathVariable(name = "uuid") String uuid, @RequestParam(name = "ref_id") String referenceId, Model model) {

        TransactionPaymentCredentialResult transactionPaymentCredentialResult = samsungPayClient.getPaymentCredentials(uuid, referenceId);

        model.addAttribute("userData", credentialResultHashMap.get(uuid));
        model.addAttribute("uuid", uuid);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String payData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transactionPaymentCredentialResult);
            DatatransTransaction dtTransaction = datatransClient.authorize(payData);
            samsungPayClient.notifyTransaction(referenceId);
            model.addAttribute("xmlRequest", dtTransaction.request);
            model.addAttribute("xmlResponse", dtTransaction.response);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "result";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public @ResponseBody TransactionCreateResult pay(@RequestParam("amount") String amount,@RequestParam("currency") String currency){
        UUID sessionId = UUID.randomUUID();

        TransactionCreateResult transactionCreateResult = samsungPayClient.createTransaction(amount,currency,sessionId);
        transactionCreateResult.setCallbackSessionId(sessionId.toString());
        credentialResultHashMap.put(sessionId.toString(),"User UID: " +sessionId.toString());

        return transactionCreateResult;

    }
}
