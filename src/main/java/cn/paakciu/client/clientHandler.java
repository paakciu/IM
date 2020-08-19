package cn.paakciu.client;

import cn.paakciu.config.StringTable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 这个类是在netty_client里面用的，逻辑处理器，用于处理客户端连接后向服务端写数据
 */
public class clientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //super.channelActive(ctx);
        System.out.println(new Date() +"：客户端写出数据");

        //1.获取数据
        ByteBuf buffer=getByteBuf(ctx);
        //2.写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx)
    {
        //1.获取二进制抽象 ByteBuf
        ByteBuf buffer =ctx.alloc().buffer();

        //2.准备数据，指定字符串是utf-8
        byte[] bytes ="paakciu 测试发送!".getBytes(Charset.forName(StringTable.charsetName));

        //3.填充数据到ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }
}
