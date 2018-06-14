package ch.datatrans.samsungpay.domain;

public class PaymentDetails {

    private Service service = null;
    private String orderNumber = null;
    private boolean recurring = false;

    private Protocol protocol = null;
    private Amount amount = null;
    private Merchant merchant = null;

    private String[] brands = null;


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String[] getBrands() {
        return brands;
    }

    public void setBrands(String[] brands) {
        this.brands = brands;
    }
}
