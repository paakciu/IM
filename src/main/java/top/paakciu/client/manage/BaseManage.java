package top.paakciu.client.manage;

import top.paakciu.client.listener.*;

/**
 * @author paakciu
 * @ClassName: BaseManage
 * @date: 2021/4/4 19:59
 */
public abstract class BaseManage<Packet,ReturnThis> {

    public abstract ReturnThis setSendSuccessListener(SendSuccessListener listener);
    public abstract ReturnThis setSendFailListener(SendFailListener listener);
    public abstract ReturnThis setHandlerListener(HandlerListener<Packet> listener);
//    public abstract ReturnThis setSuccessListener(SuccessListener<Packet> listener);
//    public abstract ReturnThis setFailListener(FailListener<Packet> listener);
}
