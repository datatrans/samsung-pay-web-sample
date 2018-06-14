package ch.datatrans.samsungpay.domain;

public class EncInfo {

    private String keyId = null;
    private String mod = null;
    private String exp = null;

    public String getKeyId() {
        return keyId;
    }

    public String getMod() {
        return mod;
    }

    public String getExp() {
        return exp;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
