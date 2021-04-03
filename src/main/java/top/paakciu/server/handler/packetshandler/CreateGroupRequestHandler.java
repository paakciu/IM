package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import top.paakciu.protocal.packet.CreateGroupRequestPacket;
import top.paakciu.protocal.packet.CreateGroupResponsePacket;
import top.paakciu.utils.AttributesHelper;

import javax.management.Attribute;
import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GreateGroupRequestHandler
 * @date: 2021/4/2 13:54
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<Long> userIdList=msg.getUserIdList();
        List<String> userNameList=new ArrayList<>();

        //分组
        ChannelGroup channelGroup=new DefaultChannelGroup(ctx.executor());
        //构建群组
        for (Long userid : userIdList) {
            Channel channel= AttributesHelper.getChannel(userid);
            if(channel!=null){
                channelGroup.add(channel);
                userNameList.add(AttributesHelper.getChannelUser(channel).getUserName());
            }
        }

        //创建群聊
        CreateGroupResponsePacket createGroupResponsePacket=new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUserNameList(userIdList);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("群创建成功，群里面有："+createGroupResponsePacket.getUserNameList());
    }

}
