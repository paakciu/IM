package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.CreateGroupResponsePacket;

/**
 * @author paakciu
 * @ClassName: CreateGroupResponseHandler
 * @date: 2021/4/2 16:58
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        if(createGroupResponsePacket.isSuccess()) {
            System.out.println("群创建成功，群里面有："+createGroupResponsePacket.getUserNameList());
        }else{

        }
    }
}
