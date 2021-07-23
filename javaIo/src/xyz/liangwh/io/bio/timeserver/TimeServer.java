package xyz.liangwh.io.bio.timeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {


    public static void main(String[] args) throws IOException {
        int port = 8080;

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket client = null;
            while (true){
                client= server.accept();
                System.out.println("一个新的客户端连接");
                new Thread(new TimeClientHandler(client)).start();
            }
        }catch (Exception e){
            server.close();
        }
    }

}
