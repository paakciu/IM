package top.paakciu.deprecated;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

@Deprecated
public class TCPClient {
    static public void main(String[] args)
    {
        // 创建一个客户端Socket对象
        Socket socket=null;
        try
        {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 4396);

            //接收输入的字符串
            Scanner sc=new Scanner(System.in);
            System.out.print("请输入一个字符串:");
            String str=sc.nextLine();
            //发送给服务器
            OutputStream out=socket.getOutputStream();
            byte[] buf=str.getBytes();
            out.write(buf);

            //接收服务器返回的字符串
            InputStream in=socket.getInputStream();
            byte[] buf1=new byte[1024];
            int len=0;
            len=in.read(buf1);
            String str2=new String(buf1, 0, len);
            System.out.println("服务器发送回来的字符串:"+str2);

            socket.close();
            sc.close();
        }
        catch (Exception  e)
        {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("服务器没有应答或者失去连接，请重试");
        }

    }
}
