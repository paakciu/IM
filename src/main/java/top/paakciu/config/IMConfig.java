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
    public static final byte[] MAGIC=new byte[]{'P','a','a','k'};
}
