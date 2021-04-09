package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.JoinGroupResponseHandler;
import top.paakciu.client.handler.SimpleChannelInboundHandlerWithHandlerFunction;
import top.paakciu.client.listener.*;
import top.paakciu.protocal.packet.BasePacket;

public abstract class BaseManageWithHandlerFunction  <Packet,Handler extends SimpleChannelInboundHandlerWithHandlerFunction ,ReturnThis>{
    protected Channel channel;
    protected Handler handler;
    private ReturnThis son;
    protected ListenWithHandlerFunction listeners;

    public BaseManageWithHandlerFunction(Channel channel,ChannelHandler handler) {
        this.handler=(Handler) handler;
        this.channel=channel;
        listeners=this.handler.getListeners();
    }
    protected ReturnThis setSon(ReturnThis self){
        this.son=self;
        return son;
    }

    public ReturnThis setSendSuccessListener(SendSuccessListener listener){
        listeners.setSendSuccessListener(listener);
        return son;
    }
    public ReturnThis setSendFailListener(SendFailListener listener){
        listeners.setSendFailListener(listener);
        return son;
    }
    public ReturnThis setHandlerListener(HandlerListener<Packet> listener){
        listeners.setHandlerListener(listener);
        return son;
    }


    protected void writeAndFlushAddListener(Channel channel, BasePacket basePacket, ListenWithHandlerFunction listeners){
        channel.writeAndFlush(basePacket).addListener(future -> {
            if(future.isSuccess()){
                if(listeners.sendsuccessListener!=null)
                    listeners.sendsuccessListener.onSendSuccess();
            }else{
                if(listeners.sendfailListener!=null)
                    listeners.sendfailListener.onSendFail();
            }
        });
    }
    protected void writeAndFlushAddListener(BasePacket basePacket){
        channel.writeAndFlush(basePacket).addListener(future -> {
            if(future.isSuccess()){
                if(listeners.sendsuccessListener!=null)
                    listeners.sendsuccessListener.onSendSuccess();
            }else{
                if(listeners.sendfailListener!=null)
                    listeners.sendfailListener.onSendFail();
            }
        });
    }
}
