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
        ByteBuf buffer=getByteBuf(ctx,"paakciu 测试发送!");

        //2.写入到信道中并刷新
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        //super.channelRead(ctx, msg);

        //收消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date()+":服务端读到数据："+byteBuf.toString(Charset.forName(StringTable.charsetName)));

    }



    private ByteBuf getByteBuf(ChannelHandlerContext ctx,String str)
    {
        //1.获取二进制抽象 ByteBuf
        ByteBuf buffer =ctx.alloc().buffer();

        //2.准备数据，指定字符串是utf-8 ,"paakciu 测试发送!"
        byte[] bytes =str.getBytes(Charset.forName(StringTable.charsetName));

        //3.填充数据到ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }
}
