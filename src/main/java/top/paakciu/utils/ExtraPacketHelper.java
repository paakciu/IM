package top.paakciu.utils;

import com.alibaba.fastjson.JSON;
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
    public static <T> T StringToObject(Class<T> clazz, String str){
        return JSON.parseObject(str,clazz);
    }
    public static String ObjectToString(Object obj){
        return JSON.toJSONString(obj);
    }
}
