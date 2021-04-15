package top.paakciu.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.FailListener;
import top.paakciu.client.listener.SendFailListener;
import top.paakciu.client.listener.SendSuccessListener;
import top.paakciu.client.listener.SuccessListener;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.LoginResponsePacket;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.info.ChannelUser;

/**
 * @author paakciu
 * @ClassName: LoginRequestHandler
 * @date: 2021/3/3 14:58
 */

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    public final static LoginResponseHandler INSTANCE=new LoginResponseHandler();

    private SendSuccessListener sendsuccessListener;
    private SendFailListener sendfailListener;
    private SuccessListener<ChannelUser> successListener;
    private FailListener<String> failListener;

    /**
     * 添加各类监视器
     * @param listener
     * @return
     */
    public LoginResponseHandler setSendSuccessListener(SendSuccessListener listener){sendsuccessListener=listener;return this;}
    public LoginResponseHandler setSendFailListener(SendFailListener listener){sendfailListener=listener;return this;}
    public LoginResponseHandler setSuccessListener(SuccessListener<ChannelUser> listener){successListener=listener;return this;}
    public LoginResponseHandler setFailListener(FailListener<String> listener){failListener=listener;return this;}


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()) {
            ChannelUser channelUser=new ChannelUser(loginResponsePacket.getUserId(), loginResponsePacket.getUserName());
            AttributesHelper.asLogin(ctx.channel());
            AttributesHelper.setChannelUser(ctx.channel(),channelUser);
            if(successListener!=null)
                //传进去一个局部变量比较好,这样修改不会影响绑定的那一份
                successListener.onSuccess(new ChannelUser(loginResponsePacket.getUserId(), loginResponsePacket.getUserName()));
        }else{
            if(failListener!=null)
                failListener.onFail(loginResponsePacket.getReason());
        }
    }

    /**
     * 提供给Client登录的接口！
     * @param client 这个参数是为了限制使用区域
     * @param channel 可用的频道
     * @param username 用户账号
     * @param passwrod 密码
     */
    public void login(Client client, Channel channel, String username, String passwrod) {
        LoginRequestPacket requestPacket = new LoginRequestPacket(username, passwrod);
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

// System.out.println(new Date() + ": 客户端登录成功");
// System.out.println("userid="+loginResponsePacket.getUserId());
//ctx.channel().attr(AttributeKey.exists("login")?AttributeKey.valueOf("login"):AttributeKey.newInstance("login")).set(true);

//System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
//重新登录
//AttributesHelper.setLoginState(ctx.channel(),3);

//super.channelActive(ctx);
//System.out.println(new Date() +"：客户端开始登陆");
//获取数据
//ByteBuf buffer=getByteBuf(ctx,"paakciu 测试发送!");
//        LoginRequestPacket loginRequestPacket=new LoginRequestPacket(
//                //UUID.randomUUID().toString(),
//                "PAAKCIU",
//                "123456"
//        );
//
//        //写入到信道中并刷新
//        ctx.channel().writeAndFlush(loginRequestPacket);