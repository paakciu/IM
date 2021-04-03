package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: ErrorMessagePacket
 * @date: 2021/3/31 14:27
 */
public class ErrorMessagePacket extends BasePacket{
    private boolean success;
    private String reason;
    private Byte errorCode;

    public Byte getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Byte errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ErrorMessagePacket{" +
                "success=" + success +
                ", reason='" + reason + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
