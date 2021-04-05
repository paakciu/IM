package top.paakciu.client.manage;

import top.paakciu.client.listener.*;
import top.paakciu.utils.ChannelUser;

/**
 * @author paakciu
 * @ClassName: BaseManage
 * @date: 2021/4/4 19:59
 */
public abstract class BaseManage<T,R> {

    public abstract R setSendSuccessListener(SendSuccessListener listener);
    public abstract R setSendFailListener(SendFailListener listener);
    public abstract R setHandlerListener(HandlerListener<T> listener);
//    public abstract R setSuccessListener(SuccessListener<T> listener);
//    public abstract R setFailListener(FailListener<T> listener);
}
