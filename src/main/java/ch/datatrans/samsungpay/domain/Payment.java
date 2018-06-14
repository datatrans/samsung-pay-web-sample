package ch.datatrans.samsungpay.domain;

public class Payment {

    private Service service = null;
    private String reference = null;
    private String status = null;
    private String provider = null;
    private Merchant merchant = null;


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

}
