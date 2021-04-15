package top.paakciu.server.handler.packetshandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.mbg.mapper.GroupMsgOfflineMapper;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMsgOffline;
import top.paakciu.protocal.packet.OffLineGroupMessageRequestPacket;
import top.paakciu.protocal.packet.OffLineGroupMessageResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.GroupInfoService;
import top.paakciu.service.GroupMessageOfflineService;
import top.paakciu.utils.AttributesHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paakciu
 * @ClassName: OffLineGroupMessageRequestHandler
 * @date: 2021/4/11 20:34
 */
@ChannelHandler.Sharable
public class OffLineGroupMessageRequestHandler extends SimpleChannelInboundHandler<OffLineGroupMessageRequestPacket> {
    public static final OffLineGroupMessageRequestHandler INSTANCE=new OffLineGroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OffLineGroupMessageRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            Long userId= AttributesHelper.getChannelUser(ctx.channel()).getUserId();

            List<GroupMsgOffline> msgOfflineList = GroupMessageOfflineService.getGroupMessageOfflineByToid(userId);
            if(msgOfflineList==null){
                OffLineGroupMessageResponsePacket responsePacket=new OffLineGroupMessageResponsePacket();
                responsePacket.setSuccess(false);
                responsePacket.setReason("没有离线消息");
                ctx.writeAndFlush(responsePacket);
                return ;
            }else{
                Map<Long,GroupInfo> groupInfoMap=new HashMap<>();
                for (GroupMsgOffline groupMsgOffline : msgOfflineList) {
                    GroupInfo info=GroupInfoService.getGroupInfoById(groupMsgOffline.getGroupid());
                    //if(info!=null){
                    groupInfoMap.put(groupMsgOffline.getGroupid(),info);
                    //}
                }

                //然后删除所有离线消息
                GroupMessageOfflineService.deleteGroupMessageOfflineByToid(userId);
                //回传发送
                OffLineGroupMessageResponsePacket responsePacket=new OffLineGroupMessageResponsePacket();
                responsePacket.setSuccess(true);
                responsePacket.setGroupInfoMap(groupInfoMap);
                responsePacket.setGroupMsgOfflineList(msgOfflineList);
                ctx.writeAndFlush(responsePacket);

            }


        });
    }
}
