package top.paakciu.utils;

import top.paakciu.protocal.SerializerAlgorithm;
import top.paakciu.protocal.serializer.Serializer;

/**
 * @author paakciu
 * @ClassName: ExtraPacketHelper
 * @date: 2021/4/3 16:31
 */
public class ExtraPacketHelper {

    private static final Serializer serializer=SerializerAlgorithm.getSerializer(SerializerAlgorithm.DEFAULT);

    public static <T> T BytesToObject(Class<T> clazz, byte[] bytes){
        return serializer.deserialize(clazz,bytes);
    }
    public static byte[] ObjectToBytes(Object obj){
        return serializer.serialize(obj);
    }
}
