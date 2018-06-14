package ch.datatrans.samsungpay.domain;

public class TransactionCreateRequest {

    private String callback = null;
    private PaymentDetails paymentDetails = null;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
