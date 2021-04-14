package top.paakciu.core;

/**
 * @author paakciu
 * @ClassName: test1
 * @date: 2021/4/12 21:47
 */
public class test1 {
    //自定义属性
    String str;
    boolean b;
    int x;
    //TODO 自定义的消息体，一定要注意GETTER跟SETTER的完备
    //下面的getter跟setter一定要对应所有属性，这是因为本IM通信协议默认使用阿里的fastJSON进行序列化
    //如果getter跟setter缺失的话会导致数据传输字段的丢失！此坑浪费了我一下午
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "test1{" +
                "str='" + str + '\'' +
                ", b=" + b +
                ", x=" + x +
                '}';
    }
}
