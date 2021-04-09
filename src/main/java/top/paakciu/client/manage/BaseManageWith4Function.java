package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.SimpleChannelInboundHandlerWith4Function;
import top.paakciu.client.handler.SimpleChannelInboundHandlerWithHandlerFunction;
import top.paakciu.client.listener.*;
import top.paakciu.protocal.packet.BasePacket;
import top.paakciu.protocal.packet.CreateGroupResponsePacket;

public abstract class BaseManageWith4Function <Packet,Handler extends SimpleChannelInboundHandlerWith4Function,ReturnThis> {
    protected Channel channel;
    protected Handler handler;
    private ReturnThis son;
    protected ListenWith4Function listeners;

    public BaseManageWith4Function(Channel channel, ChannelHandler handler) {
        this.handler=(Handler) handler;
        this.channel=channel;
        listeners=this.handler.getListeners();
    }
    protected ReturnThis setSon(ReturnThis self){
        this.son=self;
        return son;
    }

    public ReturnThis setSendSuccessListener(SendSuccessListener listener) {
        listeners.setSendSuccessListener(listener);
        return son;
    }
    public ReturnThis setSendFailListener(SendFailListener listener) {
        listeners.setSendFailListener(listener);
        return son;
    }
    public ReturnThis setSuccessListener(SuccessListener<Packet> listener) {
        listeners.setSuccessListener(listener);
        return son;
    }
    public ReturnThis setFailListener(FailListener<Packet> listener) {
        listeners.setFailListener(listener);
        return son;
    }



    protected void writeAndFlushAddListener(Channel channel, BasePacket basePacket, ListenWith4Function listeners){
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