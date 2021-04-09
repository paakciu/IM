package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.QuitGroupResponsePacket;

/**
 * @author paakciu
 * @ClassName: QuitGroupResponseHandler
 * @date: 2021/4/3 16:10
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandlerWith4Function<QuitGroupResponsePacket> {
    public final static QuitGroupResponseHandler INSTANCE=new QuitGroupResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) {
        if (responsePacket.isSuccess()) {
            if(listeners.successListener!=null)
                listeners.successListener.onSuccess(responsePacket);
        } else {
            if(listeners.failListener!=null)
                listeners.failListener.onFail(responsePacket);
        }
//            System.out.println("退出群聊[" + responsePacket.getGroupId() + "]成功！");
//            System.out.println("退出群聊[" + responsePacket.getGroupId() + "]失败！");

    }
}