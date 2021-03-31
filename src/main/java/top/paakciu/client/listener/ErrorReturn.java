package top.paakciu.client.listener;

/**
 * @author paakciu
 * @ClassName: ErrorReturn
 * @date: 2021/3/25 13:38
 */
public class ErrorReturn {
    public boolean success=false;
    public int error_code=0;//默认0是不做处理

    public ErrorReturn(boolean success, int error_code) {
        this.success = success;
        this.error_code = error_code;
    }

    public ErrorReturn() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
