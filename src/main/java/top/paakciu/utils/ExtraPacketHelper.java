package top.paakciu.utils;

import com.alibaba.fastjson.JSON;
import top.paakciu.protocal.SerializerAlgorithm;
import top.paakciu.protocal.packet.ExtraGroupRequestPacket;
import top.paakciu.protocal.packet.ExtraGroupResponsePacket;
import top.paakciu.protocal.packet.ExtraRequestPacket;
import top.paakciu.protocal.packet.ExtraResponsePacket;
import top.paakciu.protocal.serializer.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
    public static <T> List<T> StringToArray(Class<T> clazz, String str){
        return JSON.parseArray(str,clazz);
    }
    public static String ObjectToString(Object obj){
        return JSON.toJSONString(obj);
    }


    public static <T> ExtraRequestPacket toByte(Integer type, T msg, ExtraRequestPacket extraRequestPacket){
        extraRequestPacket.setType(type);
        byte[] bytes=ObjectToBytes(msg);
        extraRequestPacket.setLen(bytes.length);
        //TODO 这里可以检测加工的长度是否可以符合规范
        extraRequestPacket.setMessage(bytes);
        return extraRequestPacket;
    }
    public static <T> ExtraGroupRequestPacket toByte(Integer type, T msg, ExtraGroupRequestPacket extraGroupRequestPacket){
        extraGroupRequestPacket.setType(type);
        byte[] bytes=ObjectToBytes(msg);
        extraGroupRequestPacket.setLen(bytes.length);
        //TODO 这里可以检测加工的长度是否可以符合规范
        extraGroupRequestPacket.setMessage(bytes);
        return extraGroupRequestPacket;
    }
    public static <T> T getObject(Class<T> clazz,ExtraResponsePacket msg){
        return BytesToObject(clazz,msg.getMessage());
    }
    public static <T> T getObject(Class<T> clazz, ExtraGroupResponsePacket msg){
        return BytesToObject(clazz,msg.getMessage());
    }

    //public static final ExtraPacketHelper INSTANCE=new ExtraPacketHelper();
    //使用线程同步的两个
//    private List<Class> list=new CopyOnWriteArrayList<Class>();
//    private ConcurrentHashMap<Class, Integer> map=new ConcurrentHashMap<Class,Integer>();
//    private Integer size=0;
//    //一定要保证两个客户端的类型对应起来
//    public <T> boolean addClass(Class<T> clazz){
//        if(list.contains(clazz)&&map.contains(clazz)){
//            return false;
//        }
//        list.add(clazz);
//        map.put(clazz,size++);
//        return true;
//    }
//    public <T> boolean removeClass(Class<T> clazz){
//        if(list.contains(clazz)&&map.contains(clazz)){
//            list.remove(clazz);
//            map.remove(clazz);
//            return true;
//        }
//        return false;
//    }
//    public <T> Class<T> getClass(int i){
//        return list.get(i);
//    }
//
//    public <T> T getObject(ExtraResponsePacket msg){
//        Class<T> cz=getClass(msg.getType());
//        return BytesToObject(cz,msg.getMessage());
//    }
//    //一定要在映射包里,此函数可以帮助封装
//    public <T> ExtraRequestPacket toByte(Class<T> clazz, T box, ExtraRequestPacket extraRequestPacket){
//        if(list.contains(clazz)&&map.contains(clazz)){
//            extraRequestPacket.setType(map.get(clazz));
//            byte[] bytes=ObjectToBytes(box);
//            extraRequestPacket.setLen(bytes.length);
//            //TODO 这里可以检测加工的长度是否可以符合规范
//            extraRequestPacket.setMessage(bytes);
//
//            return extraRequestPacket;
//        }else{
//            return null;
//        }
//    }

}
