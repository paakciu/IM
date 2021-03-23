package top.paakciu.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.RegisterRequestPacket;
import top.paakciu.service.UserService;

/**
 * @author paakciu
 * @ClassName: RegisterRequestHandler
 * @date: 2021/3/22 17:11
 */
@ChannelHandler.Sharable
public class RegisterRequestHandler extends SimpleChannelInboundHandler<RegisterRequestPacket>{

    //单例
    public static final RegisterRequestHandler INSTANCE = new RegisterRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestPacket msg) throws Exception {
        //应当先验证msg内容是否合规
        if()
        Integer ref= UserService.register(,"haha");
        if(ref!=null){
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }
    }


}