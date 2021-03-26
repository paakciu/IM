package top.paakciu.deprecated;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Deprecated
public class TCPServer
{
    public TCPServer() {
    }

    public static void main(String[] args) {
        TCPServer.sartListen();
    }

    public static void sartListen()
    {
        //port为端口号
        int i = 1;int PORT=4396;
        //服务器套接字
        ServerSocket server = null;
        //客户机套接字
        Socket client = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("服务器正在监听端口port " + server.getLocalPort());
            for (;;) {
                client = server.accept();
                (new ConnectionThread(client, i)).start();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}

//连接线程
@Deprecated
class ConnectionThread extends Thread
{
    private Socket client; //
    private int count; //
    public ConnectionThread(Socket cl, int c) {
        client = cl;
        count = c;
    }
    //多线程执行函数
    public void run()
    {

        //客户机IP，即目标地址
        String destIP = client.getInetAddress().toString();
        //客户机端口IP，即目标端口
        int destport = client.getPort();
        System.out.println("监听到客户端连接，连接号为："+count+" IP地址为:"+destIP+" 端口为："+destport);

        //接收字符串
        InputStream in;
        try {
            in = client.getInputStream();
            byte[] buf = new byte[1024];
            in.read(buf);
            String str = new String(buf);
            System.out.println("服务器端接收到字符串:"+str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //发送字符串
        String str="服务端：收到你的消息了";
        byte[] buf1 = str.getBytes();
        OutputStream out;
        try {
            out = client.getOutputStream();
            out.write(buf1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}