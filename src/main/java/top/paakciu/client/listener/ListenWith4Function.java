package top.paakciu.client.listener;

import top.paakciu.protocal.packet.MessageResponsePacket;

public class ListenWith4Function<S,F> {
    public SendSuccessListener sendsuccessListener;
    public SendFailListener sendfailListener;
    public SuccessListener<S> successListener;
    public FailListener<F> failListener;
    
    /**
     * 添加各类监视器
     * @param listener
     * @return
     */
    public void setSendSuccessListener(SendSuccessListener listener){sendsuccessListener=listener;}
    public void setSendFailListener(SendFailListener listener){sendfailListener=listener;}
    public void setSuccessListener(SuccessListener<S> listener){successListener=listener;}
    public void setFailListener(FailListener<F> listener){failListener=listener;}
}
