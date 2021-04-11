package top.paakciu.client.manage;

import io.netty.channel.Channel;
import top.paakciu.client.handler.MessageResponseHandler;
import top.paakciu.client.listener.*;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.MessageRequestPacket;
import top.paakciu.protocal.packet.MessageResponsePacket;
import top.paakciu.utils.AttributesHelper;

/**
 * @author paakciu
 * @ClassName: NormalMessageManage
 * @date: 2021/4/4 19:52
 */
public class NormalMessageManage extends BaseManage<MessageResponsePacket,NormalMessageManage>{
    private MessageResponseHandler messageResponseHandler;
    private Channel channel;

    public NormalMessageManage( Channel channel,MessageResponseHandler messageResponseHandler) {
        this.messageResponseHandler = messageResponseHandler;
        this.channel = channel;
    }

    public NormalMessageManage send(Long toId, String msg) {
        Long thisId=AttributesHelper.getChannelUser(channel).getUserId();
        MessageRequestPacket messageRequestPacket=new MessageRequestPacket();
        messageRequestPacket.setToUserId(toId);
        messageRequestPacket.setFromUserId(thisId);
        messageRequestPacket.setMessage(msg);
        if(toId==thisId){
            SendFailListener listener=messageResponseHandler.getSendfailListener();
            if(listener!=null)
                listener.onSendFail();
            return this;
        }
        channel.writeAndFlush(messageRequestPacket).addListener((future -> {
            if(future.isSuccess()){
                SendSuccessListener listener=messageResponseHandler.getSendsuccessListener();
                if(listener!=null)
                    listener.onSendSuccess();
            }else {
                SendFailListener listener=messageResponseHandler.getSendfailListener();
                if(listener!=null)
                    listener.onSendFail();
            }
        }));
        return this;
    }
    @Override
    public NormalMessageManage setSendSuccessListener(SendSuccessListener listener) {
        messageResponseHandler.setSendSuccessListener(listener);
        return this;
    }

    @Override
    public NormalMessageManage setSendFailListener(SendFailListener listener) {
        messageResponseHandler.setSendFailListener(listener);
        return this;
    }

    @Override
    public NormalMessageManage setHandlerListener(HandlerListener<MessageResponsePacket> listener) {
        messageResponseHandler.setHandlerListener(listener);
        return this;
    }
//    @Override
//    public NormalMessageManage setSuccessListener(SuccessListener<MessageResponsePacket> listener) {
//        messageResponseHandler.setSuccessListener(listener);
//        return this;
//    }
//
//    @Override
//    public NormalMessageManage setFailListener(FailListener<MessageResponsePacket> listener) {
//        messageResponseHandler.setFailListener(listener);
//        return this;
//    }


}
