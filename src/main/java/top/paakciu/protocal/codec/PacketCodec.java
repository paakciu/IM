package top.paakciu.protocal.codec;

import io.netty.buffer.ByteBuf;
import top.paakciu.config.IMConfig;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.SerializerAlgorithm;
import top.paakciu.protocal.packet.BasePacket;
import top.paakciu.protocal.serializer.Serializer;

/**
 * PacketCodeC
 * 默认的包编码译码器
 * @author paakciu
 * @date 2020/12/21 11:12
 */
public class PacketCodec {

    /**
     * 魔数
     */
    public static final byte[] MAGIC_NUMBER= IMConfig.MAGIC;
    public static final int MAGICINT=IMConfig.MAGICINT;
    /**
     * 这些是跟包编码/解码相关的参数，一定是绑定这个 类的
     * {@link PacketCodec}
     */
    public static final int LENGTHFIELDOFFSET=7;
    public static final int LENGTHFIELDLENGTH=4;
    public static final int MAXFRAMELENGTH=Integer.MAX_VALUE;

    /**
     * 编码
     * @param byteBuf
     * @param packet
     * @return
     */
    public static ByteBuf encode(ByteBuf byteBuf, BasePacket packet) {
        //序列器序列化成字节流
        byte[] bytes=packet.toBytes();
        //装填编码包
        //  魔数
        byteBuf.writeBytes(MAGIC_NUMBER);
        //  封包协议版本
        byteBuf.writeByte(packet.getVersion());
        //  序列算法标识号
        byteBuf.writeByte(packet.getSerializerAlgorithm());
        //  指令（控制号）
        byteBuf.writeByte(packet.getCommand());
        //  数据长度,这里使用int型，如果带符号长度可携带2G数据，无符号则4G
        byteBuf.writeInt(bytes.length);
        //  数据体
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    /**
     * 译码
     * @param byteBuf
     * @return
     */
    public static BasePacket decode(ByteBuf byteBuf) {
        // 读取magic number
        int Magic=byteBuf.readInt();
        // 版本号
        byte version = byteBuf.readByte();//skipBytes
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        /**
         * 使用{@link PacketsCommandMapping}获取类型
         */
        Class<? extends BasePacket> requestType = PacketsCommandMapping.getRequestType(command);
        /**
         * 使用{@link SerializerAlgorithm}获取序列化方法
         */
        Serializer serializer = SerializerAlgorithm.getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        //如果出了问题
        return null;
    }

}
