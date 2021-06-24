package xyz.liangwh.io.socket;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket 普通写法
 * BIO
 */
public class SocketDemo {
    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket socket = new ServerSocket(8180);
        System.out.println("socket is open :"+8180);
        //死循环接受客户端连接
        while (true){
            //阻塞等待接受客户端连接请求 accept
            Socket client = socket.accept();
            System.out.println("accept client :["+client.getInetAddress()+":"+client.getPort()+"]");
            //接受到client，另起线程处理socket中的数据流
            new Thread(()->{
                InputStream in = null;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true){
                        String dataline = reader.readLine();
                        if(dataline!=null){
                            System.out.println(dataline);
                        }else {
                            client.close();
                            break;
                        }
                    }
                    System.out.println("client close!");
                }catch (Exception e){
                        e.printStackTrace();
                }
            }).start();

        }

    }
}
