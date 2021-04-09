package top.paakciu.client.listener;

import top.paakciu.protocal.packet.MessageResponsePacket;

public class ListenWithHandlerFunction<Packet> {
    public SendSuccessListener sendsuccessListener;
    public SendFailListener sendfailListener;
    public HandlerListener<Packet> handlerListener;
//    private SuccessListener<MessageResponsePacket> successListener;
//    private FailListener<MessageResponsePacket> failListener




    /**
     * 添加各类监视器
     * @param listener
     * @return
     */
    public void setHandlerListener(HandlerListener<Packet> listener) { handlerListener = listener; }
    public void setSendSuccessListener(SendSuccessListener listener){sendsuccessListener=listener;}
    public void setSendFailListener(SendFailListener listener){sendfailListener=listener;}
}
