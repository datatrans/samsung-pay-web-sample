package ch.datatrans.samsungpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionPaymentCredentialResult {

    private String resultCode = null;
    private String resultMessage = null;

    private String wallet_dm_id = null;
    private String method = null;
    private String card_last4digits = null;
    private String card_brand = null;

    @JsonProperty("3DS")
    private ThreeDData threeDs = null;

    private Certificate[] certificates = null;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getWallet_dm_id() {
        return wallet_dm_id;
    }

    public void setWallet_dm_id(String wallet_dm_id) {
        this.wallet_dm_id = wallet_dm_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCard_last4digits() {
        return card_last4digits;
    }

    public void setCard_last4digits(String card_last4digits) {
        this.card_last4digits = card_last4digits;
    }

    public String getCard_brand() {
        return card_brand;
    }

    public void setCard_brand(String card_brand) {
        this.card_brand = card_brand;
    }

    public ThreeDData getThreeDs() {
        return threeDs;
    }

    public void setThreeDs(ThreeDData threeDs) {
        this.threeDs = threeDs;
    }

    public Certificate[] getCertificates() {
        return certificates;
    }

    public void setCertificates(Certificate[] certificates) {
        this.certificates = certificates;
    }
}
