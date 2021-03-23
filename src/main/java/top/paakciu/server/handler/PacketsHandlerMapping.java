package top.paakciu.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.BasePacket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paakciu
 * @ClassName: PacketsHandlerMapping
 * @date: 2021/3/10 13:24
 */
public class PacketsHandlerMapping extends SimpleChannelInboundHandler<BasePacket>{

    public static final PacketsHandlerMapping INSTANCE=new PacketsHandlerMapping();

    private Map<Byte, SimpleChannelInboundHandler<? extends BasePacket>> handlerMap;

    public PacketsHandlerMapping() {
        handlerMap = new HashMap<>();

        //登录鉴权之前的handler要单独处理
        //handlerMap.put(PacketsCommandMapping.LOGIN_REQUEST, LoginRequestHandler.INSTANCE);
        //handlerMap.put(PacketsCommandMapping.MESSAGE, MessageRequestHandler.INSTANCE);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BasePacket msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
