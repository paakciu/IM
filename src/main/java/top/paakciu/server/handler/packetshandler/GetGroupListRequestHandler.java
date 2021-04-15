package top.paakciu.server.handler.packetshandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.protocal.packet.GetGroupListRequestPacket;
import top.paakciu.protocal.packet.GetGroupListResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.GroupInfoService;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetGroupListRequestHandler
 * @date: 2021/4/15 12:32
 */
@ChannelHandler.Sharable
public class GetGroupListRequestHandler extends SimpleChannelInboundHandler<GetGroupListRequestPacket> {

    public static final GetGroupListRequestHandler INSTANCE=new GetGroupListRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetGroupListRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            Long userId=msg.getUserId();
            List<GroupInfo> groups = GroupInfoService.getGroupsByUserId(userId);


            if(groups==null){//||groupsByUserId.size()==0 应当也正常返回，这个只是正常的没有加群罢了，不算出错
                //获取错误了
                GetGroupListResponsePacket responsePacket=new GetGroupListResponsePacket();
                responsePacket.setSuccess(false);
                responsePacket.setUserId(userId);
                responsePacket.setReason("查不到关于该用户的群组消息，出现意外");
                ctx.writeAndFlush(responsePacket);
                return;
            }else{
                GetGroupListResponsePacket responsePacket=new GetGroupListResponsePacket();
                responsePacket.setSuccess(true);
                responsePacket.setUserId(msg.getUserId());
                responsePacket.setGroupInfoList(groups);
                ctx.writeAndFlush(responsePacket);
            }
        });
    }
}
