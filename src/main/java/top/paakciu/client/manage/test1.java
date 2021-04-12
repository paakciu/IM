package top.paakciu.client.manage;

/**
 * @author paakciu
 * @ClassName: test1
 * @date: 2021/4/12 21:47
 */
public class test1 {
    String str;
    boolean b;
    int x;

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
