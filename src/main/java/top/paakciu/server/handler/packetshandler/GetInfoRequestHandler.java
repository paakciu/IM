package top.paakciu.server.handler.packetshandler;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.GetInfoRequestPacket;

/**
 * @author paakciu
 * @ClassName: GetInfoRequestHandler
 * @date: 2021/4/15 19:49
 */
@ChannelHandler.Sharable
public class GetInfoRequestHandler extends SimpleChannelInboundHandler<GetInfoRequestPacket> {
    public static final GetInfoRequestHandler INSTANCE=new GetInfoRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetInfoRequestPacket msg) throws Exception {

    }
}
