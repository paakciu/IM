package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.ErrorMessagePacket;


/**
 * @author paakciu
 * @ClassName: ErrorMessageHandler
 * @date: 2021/3/31 14:33
 */

public class ErrorMessageHandler extends SimpleChannelInboundHandler<ErrorMessagePacket> {
    public final static ErrorMessageHandler INSTANCE=new ErrorMessageHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ErrorMessagePacket msg) throws Exception {
        System.out.println("收到出问题的消息"+msg.getReason()+msg.getErrorCode());
    }
}
