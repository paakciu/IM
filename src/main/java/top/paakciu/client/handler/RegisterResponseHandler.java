package top.paakciu.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.*;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.RegisterRequestPacket;
import top.paakciu.protocal.packet.RegisterResponsePacket;

/**
 * @author paakciu
 * @ClassName: RegisterResponseHandler
 * @date: 2021/3/26 0:09
 */
public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponsePacket> {
    public final static RegisterResponseHandler INSTANCE=new RegisterResponseHandler();


    private SendSuccessListener sendsuccessListener;
    private SendFailListener sendfailListener;
    private SuccessListener<String> successListener;
    private FailListener<String> failListener;

    /**
     * 添加各类监视器
     * @param listener
     * @return
     */
    public RegisterResponseHandler setSendSuccessListener(SendSuccessListener listener){sendsuccessListener=listener;return this;}
    public RegisterResponseHandler setSendFailListener(SendFailListener listener){sendfailListener=listener;return this;}
    public RegisterResponseHandler setSuccessListener(SuccessListener<String> listener){successListener=listener;return this;}
    public RegisterResponseHandler setFailListener(FailListener<String> listener){failListener=listener;return this;}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterResponsePacket msg) throws Exception {
        if(msg.isSuccess()) {
            if(successListener!=null)
                successListener.onSuccess(msg.getMsg());
        }else{
            if(failListener!=null)
                failListener.onFail(msg.getMsg());
        }
    }

    /**
     * 提供给Client注册的接口！
     * @param client 这个参数是为了限制使用区域
     * @param channel 可用的频道
     * @param username 用户账号
     * @param passwrod 密码
     */
    public void regiter(Client client, Channel channel, String username, String passwrod) {
        RegisterRequestPacket requestPacket = new RegisterRequestPacket(username, passwrod);
        channel.writeAndFlush(requestPacket).addListener(future -> {
            if(future.isSuccess()){
                if(sendsuccessListener!=null)
                    sendsuccessListener.onSendSuccess();
            }else{
                if(sendfailListener!=null)
                    sendfailListener.onSendFail();
            }
        });
    }
}


//    private PaakciuFuture future;
//    public PaakciuFuture getFuture() {
//        return future;
//    }
//    public void setFuture(PaakciuFuture future) {
//        this.future = future;
//    }
//if(future!=null&&future.getListener()!=null)
//        future.getListener().onSuccess(String.class,msg.getMsg());
//if(future!=null&&future.getListener()!=null)
//        future.getListener().onFail(String.class,msg.getMsg());