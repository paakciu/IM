package top.paakciu.server.handler.packetshandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.PullMessageRequestPacket;
import top.paakciu.server.NettyServer;

/**
 * @author paakciu
 * @ClassName: PullMessageRequestHandler
 * @date: 2021/4/11 22:16
 */
public class PullMessageRequestHandler extends SimpleChannelInboundHandler<PullMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PullMessageRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
//            private boolean isGroup=false;
//            private Long fromId;//
//            private Long id1;
//            private Long id2;
//            private Long fromMessageId=-1L;//默认
//            private boolean getBigger=true;//根据消息ID往下还是往上数消息数
//            private Integer nums=-1;//拉取多少个

            if(msg.isGroup()){
                //群聊的处理
            }else {
                //单聊的处理
                if(msg.getFromMessageId()==-1){
                    //TODO 第一件事要做这个
                }
            }

        });
    }
}
