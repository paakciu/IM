package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.ExtraRequestPacket;
import top.paakciu.protocal.packet.ExtraResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.info.ChannelUser;

import java.util.Date;

/**
 * @author paakciu
 * @ClassName: ExtraRequestHandler
 * @date: 2021/4/12 20:48
 */
@ChannelHandler.Sharable
public class ExtraRequestHandler extends SimpleChannelInboundHandler<ExtraRequestPacket> {
    public static final ExtraRequestHandler INSTANCE=new ExtraRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ExtraRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()-> {
            //System.out.println(new Date() + ": 收到客户端消息: " + msg.getMessage());
            //拿到发送方的信息
            ChannelUser channelUser = AttributesHelper.getChannelUser(ctx.channel());
            //拿到消息接收方的 channel
            Channel toChannel = AttributesHelper.getChannel(msg.getToUserId());
            //如果消息方不在线
            if(toChannel==null)return ;

            // 不能自己发给自己
            if (toChannel == ctx.channel()) {
                String str = "服务器拒绝！不能发送给自己！";
                ErrorMessagePacket errorMessagePacket = new ErrorMessagePacket();
                errorMessagePacket.setSuccess(false);
                errorMessagePacket.setErrorCode(PacketsCommandMapping.EXTRA_REQUEST);
                errorMessagePacket.setReason(str);
                ctx.channel().writeAndFlush(errorMessagePacket);
                System.out.println(str);
                return;
            }
            ExtraResponsePacket responsePacket=new ExtraResponsePacket();
            responsePacket.setDate(new Date());
            responsePacket.setFromUserId(channelUser.getUserId());
            responsePacket.setFromUserName(channelUser.getUserName());
            responsePacket.setToUserId(msg.getToUserId());
            responsePacket.setLen(msg.getLen());
            responsePacket.setType(msg.getType());
            responsePacket.setMessage(msg.getMessage());

            //该消息暂定自己不会收到，因为有可能存在大容量消息。
            toChannel.writeAndFlush(responsePacket);
        });
    }
}
