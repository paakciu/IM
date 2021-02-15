package top.paakciu.protocal.constant;

import top.paakciu.protocal.Serializer;

/**
 * 指示用什么序列化方法的常量类（工厂类）
 * SerializerAlgorithm
 * @author paakciu
 * @date 2020/12/21 10:04
 */
public final class SerializerAlgorithm {
    /**
     * json序列化标识
     */
    public final static byte JSON=1;

    public static Serializer getSerializer(byte serializeAlgorithm)
    {

    }

}
