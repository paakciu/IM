package top.paakciu.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.mbg.model.User;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.LoginResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.UserService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.info.ChannelUser;
import top.paakciu.utils.GroupsHelper;

/**
 * @author paakciu
 * @ClassName: LoginRequestHandler
 * @date: 2021/3/3 18:08
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    //单例
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        NettyServer.executor.submit(()-> {
            System.out.println("收到登录请求"+loginRequestPacket.getUsername()+" "+loginRequestPacket.getPassword());
            //准备响应
            LoginResponsePacket response= new LoginResponsePacket();

            //唯一标识符---------这里逻辑需要完善
            //String uuid= UUID.nameUUIDFromBytes(loginRequestPacket.getUserId().getBytes(StandardCharsets.UTF_8)).toString();
            //可以在uuid后面加数据库的自增id
//        String uuid=UUID.randomUUID().toString();
//        response.setUserId(uuid);
//        Session session=new Session(uuid, loginRequestPacket.getUsername());
//        AttributesHelper.bindSession(ctx.channel(),session);

            // 在这里要获取数据库返回的编号大小，这个一定要返回呀。
            User user=getUser(loginRequestPacket);
            // 登录校验
            if (valid(user)) {
                // 校验成功
                System.out.println("登录成功！");
                ChannelUser channelUser=new ChannelUser(user.getId(),user.getUsername());

                //TODO 登录成功要解决-channel的映射，channelgroup的登录！
                AttributesHelper.asLogin(ctx.channel());
                AttributesHelper.setChannelUser(ctx.channel(),channelUser);

                GroupsHelper.UserLoginAboutGroup(ctx,channelUser.getUserId());


                response.setUserId(user.getId());
                response.setSuccess(true);
                response.setUserName(user.getUsername());

            } else {
                // 校验失败
                response.setSuccess(false);
                response.setReason("账号密码校验失败");
            }

            //ByteBuf buff= PacketCodec.encode(ctx.alloc().buffer(),response);
            //ctx.channel().writeAndFlush(response);
            //更改传播路径，更快到达编码器
            ctx.writeAndFlush(response);
            //不调用父类方法就不会传递到下个节点
        });
    }


    //用户断线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //取消绑定
        AttributesHelper.asLogout(ctx.channel());
        AttributesHelper.removeChannelUser(ctx.channel());

        //TODO 离线要解决-channel的映射，channelgroup的离开！
        GroupsHelper.UserLogoutAboutGroup(ctx.channel());

        //这个需要父类执行吗，我这里只是额外进行了一步，如果我不写上句，它默认就是会执行父类方法的，故此我判断需要保留父类
        super.channelInactive(ctx);
    }

    private User getUser(LoginRequestPacket loginRequestPacket){
        User user=UserService.getUserByUserNameAndPassword(loginRequestPacket.getUsername(),loginRequestPacket.getPassword());
        return user;
    }
    //这里一般要验证数据库
    private boolean valid(User user) {
        if(user!=null)
        {
            return true;
        }else{
            return false;
        }
    }
}
