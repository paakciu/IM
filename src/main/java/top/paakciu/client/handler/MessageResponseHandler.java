package top.paakciu.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.*;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.MessageResponsePacket;



import java.util.Date;

/**
 * @author paakciu
 * @ClassName: MessageRequestHandler
 * @date: 2021/3/3 16:53
 */

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    public final static MessageResponseHandler INSTANCE=new MessageResponseHandler();

    private SendSuccessListener sendsuccessListener;
    private SendFailListener sendfailListener;
    private HandlerListener<MessageResponsePacket> handlerListener;
//    private SuccessListener<MessageResponsePacket> successListener;
//    private FailListener<MessageResponsePacket> failListener




    /**
     * 添加各类监视器
     * @param listener
     * @return
     */
    public void setHandlerListener(HandlerListener<MessageResponsePacket> listener) { handlerListener = listener; }
    public void setSendSuccessListener(SendSuccessListener listener){sendsuccessListener=listener;}
    public void setSendFailListener(SendFailListener listener){sendfailListener=listener;}
//    public void setSuccessListener(SuccessListener<MessageResponsePacket> listener){successListener=listener;}
//    public void setFailListener(FailListener<MessageResponsePacket> listener){failListener=listener;}

    public SendSuccessListener getSendsuccessListener() {
        return sendsuccessListener;
    }

    public SendFailListener getSendfailListener() {
        return sendfailListener;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
//        messageResponsePacket.get
        //todo 会包含自己的返回消息
        if(handlerListener!=null)
            handlerListener.onHandle(messageResponsePacket);
    }

}
