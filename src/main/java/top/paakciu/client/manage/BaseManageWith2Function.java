package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.SimpleChannelInboundHandlerWith2Function;
import top.paakciu.client.handler.SimpleChannelInboundHandlerWithHandlerFunction;
import top.paakciu.client.listener.ListenWith2Funciton;
import top.paakciu.client.listener.SendFailListener;
import top.paakciu.client.listener.SendSuccessListener;
import top.paakciu.protocal.packet.BasePacket;

/**
 * @author paakciu
 * @ClassName: BaseManageWith2Function
 * @date: 2021/4/12 14:29
 */
public class BaseManageWith2Function <ReturnThis>{
    protected Channel channel;
    private ReturnThis son;
    //因为不存在handler，直接在函数管理下增加监听就可以了
    protected ListenWith2Funciton listeners=new ListenWith2Funciton();

    protected BaseManageWith2Function(Channel channel) {
        this.channel=channel;
    }
    protected ReturnThis setSon(ReturnThis self){
        this.son=self;
        return son;
    }

    protected void writeAndFlushAddListener(BasePacket basePacket){
        if(channel!=null)
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


    public ReturnThis setSendSuccessListener(SendSuccessListener listener) {
        listeners.setSendSuccessListener(listener);
        return son;
    }
    public ReturnThis setSendFailListener(SendFailListener listener) {
        listeners.setSendFailListener(listener);
        return son;
    }

}
