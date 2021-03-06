package top.paakciu.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import top.paakciu.protocal.packet.BasePacket;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.LoginResponsePacket;
import top.paakciu.protocal.codec.PacketCodec;
import top.paakciu.protocal.packet.MessagePacket;
import top.paakciu.utils.AttributesHelper;

import java.util.Date;
import java.util.UUID;

/**
 * 这个类是在netty_client里面用的，逻辑处理器，用于处理客户端连接后向服务端写数据
 */
@Deprecated
public class clientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //super.channelActive(ctx);
        System.out.println(new Date() +"：客户端开始登陆");

        //获取数据
        //ByteBuf buffer=getByteBuf(ctx,"paakciu 测试发送!");
        LoginRequestPacket loginRequestPacket=new LoginRequestPacket(
                //UUID.randomUUID().toString(),
                "PAAKCIU",
                "123456"
        );

        //编码
        ByteBuf buffer=PacketCodec.encode(ctx.alloc().buffer(),loginRequestPacket);

        //写入到信道中并刷新
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        //super.channelRead(ctx, msg);

        //收消息
        ByteBuf byteBuf = (ByteBuf) msg;
        //System.out.println(new Date()+":服务端读到数据："+byteBuf.toString(Charset.forName(StringTable.charsetName)));

        BasePacket packet = PacketCodec.decode(byteBuf);
        if(packet instanceof LoginResponsePacket)
        {
            LoginResponsePacket loginResponsePacket=(LoginResponsePacket)packet;
            if(loginResponsePacket.getSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功");
                //ctx.channel().attr(AttributeKey.exists("login")?AttributeKey.valueOf("login"):AttributeKey.newInstance("login")).set(true);
                AttributesHelper.asLogin(ctx.channel());

            }else{
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        }else if (packet instanceof MessagePacket) {
            MessagePacket messageResponsePacket = (MessagePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }

}


//    private ByteBuf getByteBuf(ChannelHandlerContext ctx,String str)
//    {
//        //1.获取二进制抽象 ByteBuf
//        ByteBuf buffer =ctx.alloc().buffer();
//
//        //2.准备数据，指定字符串是utf-8 ,"paakciu 测试发送!"
//        byte[] bytes =str.getBytes(Charset.forName(StringTable.charsetName));
//
//        //3.填充数据到ByteBuf
//        buffer.writeBytes(bytes);
//
//        return buffer;
//    }