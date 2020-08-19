package cn.paakciu.server;

import cn.paakciu.config.StringTable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 这个类在netty_server中会被调用
 */
public class serverHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        //super.channelRead(ctx, msg);

        //收到消息之后会执行这个函数
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date()+":服务端读到数据："+byteBuf.toString(Charset.forName(StringTable.charsetName)));
    }
}
