package top.paakciu.client.listener;

/**
 * 参考其中完整的使用样例：
 * {@link top.paakciu.client.handler.RegisterResponseHandler}
 */
/*
THE WHOLE THINGS 完整使用样例:
private SendSuccessListener sendsuccessListener;
private SendFailListener sendfailListener;
private SuccessListener<String> successListener;
private FailListener<String> failListener;
public RegisterResponseHandler setSendSuccessListener(SendSuccessListener listener){sendsuccessListener=listener;return this;}
public RegisterResponseHandler setSendFailListener(SendFailListener listener){sendfailListener=listener;return this;}
public RegisterResponseHandler setSuccessListener(SuccessListener<String> listener){successListener=listener;return this;}
public RegisterResponseHandler setFailListener(FailListener<String> listener){failListener=listener;return this;}
------------------------------------------------------------------------
if(future.isSuccess()){
    if(sendsuccessListener!=null)
        sendsuccessListener.onSendSuccess();
}else{
    if(sendfailListener!=null)
        sendfailListener.onSendFail();
}
------------------------------------------------------------------------
if(msg.isSuccess()) {
if(successListener!=null)
    successListener.onSuccess(msg.getMsg());
}else{
if(failListener!=null)
    failListener.onFail(msg.getMsg());
}
 */
/*
部分样例
------------------------------------------------------------------------
if(successListener!=null)
    successListener.onSuccess(str);
------------------------------------------------------------------------
 */

/**
 * @author paakciu
 * @InterfaceName: SuccessListener
 * @date: 2021/3/28 16:19
 */

public interface SuccessListener<T>{
    void onSuccess(T str);
}