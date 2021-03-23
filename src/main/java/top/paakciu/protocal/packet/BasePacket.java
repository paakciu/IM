package top.paakciu.protocal.packet;

import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.serializer.Serializer;
import top.paakciu.protocal.SerializerAlgorithm;

/**
 * @author paakciu
 */
public class BasePacket {
    /**
     * 协议版本
     */
    private Byte version=1;
    /**
     * 序列化算法
     */
    private Byte serializerAlgorithm= SerializerAlgorithm.DEFAULT;

    //使之不能初始化
    protected BasePacket(){

    }
    /**
     * 获取指令的抽象方法
     * @return
     */
    public Byte getCommand(){
        return PacketsCommandMapping.getCommand(this.getClass());
    }

    /**
     * 序列化成字节流
     * 这里会存在一个bug，就是如果序列化算法号被修改了，将有可能出现前后不一致的问题
     * @return byte[]
     */
    public byte[] toBytes(){
        //取出对应的序列化方法，进行序列化
        return SerializerAlgorithm.getSerializer(this.serializerAlgorithm)
                .serialize(this);
    }
    public Serializer getSerializer(){
        return SerializerAlgorithm.getSerializer(this.serializerAlgorithm);
    }

    //GETTER AND SETTER
    public Byte getVersion() {
        return version;
    }
    public void setVersion(Byte version) {
        this.version = version;
    }
    public Byte getSerializerAlgorithm(){return serializerAlgorithm;}
    public void setSerializerAlgorithm(Byte serializerAlgorithm){
        this.serializerAlgorithm=serializerAlgorithm;
    }
}
