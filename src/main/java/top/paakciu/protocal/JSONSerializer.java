package top.paakciu.protocal;

import top.paakciu.protocal.constant.SerializerAlgorithm;
import com.alibaba.fastjson.JSON;

/**
 * JSONSerializer
 *
 * @author paakciu
 * @date 2020/12/21 10:05
 */
public class JSONSerializer implements Serializer{
    /**
     * 序列化算法，获取具体的序列化算法标识
     *
     * @return
     */
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    /**
     * java对象转成二进制
     * @param object
     * @return
     */
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    /**
     * 二进制转换成java对象
     * @param clazz
     * @param bytes
     * @return
     */
    @Override
    public <M> M deserialize(Class<M> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
