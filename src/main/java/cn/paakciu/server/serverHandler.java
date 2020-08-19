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
    //收到消息之后会执行这个函数
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        //super.channelRead(ctx, msg);


        //收消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date()+":服务端读到数据："+byteBuf.toString(Charset.forName(StringTable.charsetName)));


        //写消息
        System.out.println(new Date()+":服务端写出数据");
        //获取要写的数据
        ByteBuf out = getByteBuf(ctx,"paakciu 测试发送!");
        //写入到信道中并刷新
        ctx.channel().writeAndFlush(out);

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
