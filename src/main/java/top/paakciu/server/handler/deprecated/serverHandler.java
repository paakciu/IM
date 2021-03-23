//package top.paakciu.server.handler.deprecated;
//
//import top.paakciu.config.StringTable;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import top.paakciu.protocal.packet.BasePacket;
//import top.paakciu.protocal.packet.LoginRequestPacket;
//import top.paakciu.protocal.packet.LoginResponsePacket;
//import top.paakciu.protocal.codec.PacketCodec;
//import top.paakciu.protocal.packet.MessagePacket;
//
//import java.nio.charset.Charset;
//import java.util.Date;
//
///**
// * 这个类在netty_server中会被调用
// */
//@Deprecated
//public class serverHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    //收到消息之后会执行这个函数
//    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
//        //super.channelRead(ctx, msg);
//
//
//        //收消息
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println(new Date()+":服务端收到数据："+byteBuf.toString(Charset.forName(StringTable.charsetName)));
//
//        //解码
//        BasePacket packet= PacketCodec.decode(byteBuf);
//
//        if(packet instanceof LoginRequestPacket){
//
//            //准备响应
//            LoginResponsePacket response= new LoginResponsePacket();
//
//            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
//            // 登录校验
//            if (valid(loginRequestPacket)) {
//                // 校验成功
//                System.out.println("模拟登录成功！");
//                response.setSuccess(true);
//
//            } else {
//                // 校验失败
//                response.setSuccess(false);
//                response.setReason("账号密码校验失败");
//            }
//
//            ByteBuf buff=PacketCodec.encode(ctx.alloc().buffer(),response);
//            ctx.channel().writeAndFlush(buff);
//        }else if(packet instanceof MessagePacket)
//        {
//            // 处理消息
//            MessagePacket messageRequestPacket = ((MessagePacket) packet);
//            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//
//            MessagePacket messageResponsePacket = new MessagePacket();
//            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
//            ByteBuf responseByteBuf = PacketCodec.encode(ctx.alloc().buffer(), messageResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
//        }
//
//    }
//    //这里一般要验证数据库
//    private boolean valid(LoginRequestPacket loginRequestPacket) {
//        return true;
//    }
//}
//
//
////        //写消息
////        System.out.println(new Date()+":服务端写出数据");
////        //获取要写的数据
////        ByteBuf out = getByteBuf(ctx,"paakciu 测试发送!");
////        //写入到信道中并刷新
////        ctx.channel().writeAndFlush(out);
//
////    private ByteBuf getByteBuf(ChannelHandlerContext ctx,String str)
////    {
////        //1.获取二进制抽象 ByteBuf
////        ByteBuf buffer =ctx.alloc().buffer();
////
////        //2.准备数据，指定字符串是utf-8 ,"paakciu 测试发送!"
////        byte[] bytes =str.getBytes(Charset.forName(StringTable.charsetName));
////
////        //3.填充数据到ByteBuf
////        buffer.writeBytes(bytes);
////
////        return buffer;
////    }