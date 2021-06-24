package xyz.liangwh.io.socket;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;

import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * NIO 类似select
 * 客户端越多，遍历越久
 */
public class SocketNIO {

    @SneakyThrows
    public static void main(String[] args) {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("1.1.1.1",99));
        server.configureBlocking(false);//非阻塞
        server.setOption(StandardSocketOptions.TCP_NODELAY,false);

        List<SocketChannel> clients = new ArrayList<>();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while(true){
            SocketChannel client = server.accept();//configureBlocking false 非阻塞 ，没有连接返回null，底层是-1
            if(client==null){
                System.out.println("no client accept");
            }else{
                System.out.println("accept client "+client.socket().getPort());
                clients.add(client);
            }

            //循环遍历clients
            for(SocketChannel c : clients ){
                int num = c.read(byteBuffer);
                if(num>0){
                    byteBuffer.flip();
                    byte[] aaa = new byte[byteBuffer.limit()];
                    byteBuffer.get(aaa);
                    String a = new String(aaa);
                    System.out.println(c.socket().getPort()+":"+a);
                    byteBuffer.clear();
                }
            }

        }

    }



}
