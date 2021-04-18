package top.paakciu.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.BasePacket;
import top.paakciu.protocal.packet.JoinGroupRequestPacket;
import top.paakciu.server.handler.packetshandler.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paakciu
 * @ClassName: PacketsHandlerMapping
 * @date: 2021/3/10 13:24
 */
@ChannelHandler.Sharable
public class PacketsHandlerMapping extends SimpleChannelInboundHandler<BasePacket>{

    public static final PacketsHandlerMapping INSTANCE=new PacketsHandlerMapping();

    private Map<Byte, SimpleChannelInboundHandler<? extends BasePacket>> handlerMap;

    public PacketsHandlerMapping() {
        handlerMap = new HashMap<>();

        //登录鉴权之前的handler要单独处理
//        handlerMap.put(PacketsCommandMapping.LOGIN_REQUEST, LoginRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.GET_GROUP_MEMBER_REQUEST, GetGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.OFF_LINE_MESSAGE_REQUEST,OffLineMessageRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.PULL_MESSAGE_REQUEST,PullMessageRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.EXTRA_REQUEST,ExtraRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.GET_GROUP_LIST_REQUEST,GetGroupListRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.OFF_LINE_GROUP_MESSAGE_REQUEST,OffLineGroupMessageRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.EXTRA_GROUP_REQUEST,ExtraGroupRequestHandler.INSTANCE);
        handlerMap.put(PacketsCommandMapping.GET_INFO_REQUEST,GetInfoAndFriendRequestHandler.INSTANCE);
//        handlerMap.put(PacketsCommandMapping.PULL_MESSAGE_REQUEST2,PullMessageRequestHandler2.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BasePacket msg) throws Exception {
        SimpleChannelInboundHandler<? extends BasePacket> handler=handlerMap.get(msg.getCommand());
        if(handler!=null)
            handler.channelRead(ctx, msg);
        else
            System.err.println("这个类型没有映射上，检查是客户端出问题还是服务器出问题"+msg);

    }

}
