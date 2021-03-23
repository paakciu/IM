package top.paakciu.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import top.paakciu.protocal.codec.PacketCodec;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.LoginResponsePacket;
import top.paakciu.utils.AttributesHelper;

import java.util.Date;
import java.util.UUID;

/**
 * @author paakciu
 * @ClassName: LoginRequestHandler
 * @date: 2021/3/3 14:58
 */

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //super.channelActive(ctx);
        System.out.println(new Date() +"：客户端开始登陆");

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

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.getSuccess()) {

            AttributesHelper.asLogin(ctx.channel());

            System.out.println(new Date() + ": 客户端登录成功");
            System.out.println("userid="+loginResponsePacket.getUserId());
            //ctx.channel().attr(AttributeKey.exists("login")?AttributeKey.valueOf("login"):AttributeKey.newInstance("login")).set(true);
        }else{
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            //重新登录
            AttributesHelper.setLoginState(ctx.channel(),3);
        }

    }
}
