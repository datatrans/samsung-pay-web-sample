package ch.datatrans.samsungpay.domain;

public class TransactionCreateResult {

    private String id = null;
    private String href = null;

    private String resultCode = null;
    private String resultMessage = null;
    private EncInfo encInfo = null;

    private String callbackSessionId = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

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

    public EncInfo getEncInfo() {
        return encInfo;
    }

    public void setEncInfo(EncInfo encInfo) {
        this.encInfo = encInfo;
    }

    public String getCallbackSessionId() {
        return callbackSessionId;
    }

    public void setCallbackSessionId(String callbackSessionId) {
        this.callbackSessionId = callbackSessionId;
    }
}
