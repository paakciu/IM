package top.paakciu.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.RegisterRequestPacket;
import top.paakciu.protocal.packet.RegisterResponsePacket;
import top.paakciu.service.UserService;

import static top.paakciu.config.IMConfig.LENGTH_LIMIT;

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
        RegisterResponsePacket responsePacket=new RegisterResponsePacket();
        //应当先验证msg内容是否合规
        if(verification(msg)){
            Integer ref= UserService.register(msg.getUsername(),msg.getPassword());
            if(ref!=null){
                //System.out.println("注册成功");
                responsePacket.setSuccess(true);
                responsePacket.setMsg("注册成功");
            }else{
                //System.out.println("注册失败");
                responsePacket.setSuccess(false);
                responsePacket.setMsg("注册失败，数据库已有该账号");
            }
        }else{
            responsePacket.setSuccess(false);
            responsePacket.setMsg("注册失败，注册账号密码不符合规范");
        }
        //更快到达编码器
        ctx.writeAndFlush(responsePacket);
    }
    //如果账号密码大于某个长度，就会拒绝注册服务
    private boolean verification(RegisterRequestPacket msg)
    {
        if(msg.getUsername().length()>LENGTH_LIMIT||msg.getPassword().length()>LENGTH_LIMIT)
            return false;
        return true;
    }
}