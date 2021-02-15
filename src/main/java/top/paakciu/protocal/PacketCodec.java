package top.paakciu.protocal;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import top.paakciu.config.IMConfig;

/**
 * PacketCodeC
 *
 * @author paakciu
 * @date 2020/12/21 11:12
 */
public class PacketCodec {
    //private static final int MAGIC_NUMBER=;
    /**
     * 魔数
     */
    private static final byte[] MAGIC_NUMBER= IMConfig.MAGIC;

    public ByteBuf encode(BasePacket packet){
        //1.创建ByteBuf对象 --netty的创建为什么是这个
        ByteBuf byteBuf= ByteBufAllocator.DEFAULT.ioBuffer();
        //2.序列化java对象
        byte[] bytes=new JSONSerializer().serialize(packet);

        //3.装填编码包
        //  魔数
        byteBuf.writeBytes(MAGIC_NUMBER);
        //  封包协议版本
        byteBuf.writeByte(packet.getVersion());
        //  序列算法标识号
        byteBuf.writeByte(1);
        //  指令（控制号）
        byteBuf.writeByte(packet.getCommand());
        //  数据长度,这里使用int型，如果带符号长度可携带2G数据，无符号则4G
        byteBuf.writeInt(bytes.length);
        //  数据体
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Object decode(ByteBuf byteBuf) {
        // 读取magic number
        int Magic=byteBuf.readInt();

        // 版本号
        byte version = byteBuf.readByte();

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

//        Class<? extends BasePacket> requestType = getRequestType(command);
//        Serializer serializer = getSerializer(serializeAlgorithm);
//
//        if (requestType != null && serializer != null) {
//            return serializer.deserialize(requestType, bytes);
//        }


        return null;
    }

}
