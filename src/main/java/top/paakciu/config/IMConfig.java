package top.paakciu.config;

/**
 * 这个类负责全局的一些账号密码配置
 * 例如说
 * 数据库的账号密码
 * 连接的IP地址和端口
 * 以及其他账号密码
 */
public class IMConfig {
    public static final String HOST="localhost";
    public static final int PORT=4396;
    //这个是魔数的字节流
    public static final byte[] MAGIC=new byte[]{'P','a','a','k'};
    //如果是为了读4个字节方便，可以使用魔数对应的int来比较结果，但是跟上者一定是要一一对应的。
    //1348559211
    public static final int MAGICINT=getIntFromBytes(MAGIC);

    public static int getIntFromBytes(byte[] by) {
        if(by.length!=4)return 1348559211;//这个“Paak”的字节流
        return (by[0] & 0xff) << 24 | (by[1] & 0xff) << 16 | (by[2] & 0xff) << 8 | by[3] & 0xff;
    }

}
