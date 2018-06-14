package ch.datatrans.samsungpay.domain;

public class NotificationRequest {

    private Payment payment = null;
    private Number timestamp = null;


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Number getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Number timestamp) {
        this.timestamp = timestamp;
    }
}
