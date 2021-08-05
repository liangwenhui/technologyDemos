package xyz.liangwh.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NioTimeClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false);
        Selector selector = Selector.open();
        client.register(selector, SelectionKey.OP_CONNECT);
        client.connect(new InetSocketAddress("localhost",8080));
        while (true){
            TimeUnit.SECONDS.sleep(1);
            selector.select(100);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey next = null;
            while (iterator.hasNext()){
                next = iterator.next();
                iterator.remove();
                if (next.isValid()){
                    if(next.isConnectable()){
                        if(client.finishConnect()){
                            System.out.println("成功连接服务器");
                            client.register(selector,SelectionKey.OP_READ);
                            ByteBuffer allocate = ByteBuffer.allocate("QUERY_TIME".getBytes().length);
                            allocate.put("QUERY_TIME".getBytes());
                            allocate.flip();
                            client.write(allocate);
                            allocate.clear();
                        }
                    }else if(next.isReadable()){
                        SocketChannel sc = (SocketChannel)next.channel();
                        System.out.println("服务端返回数据");
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(readBuffer);
                        if(readBytes>0){
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            String body = new String(bytes,"UTF-8");
                            System.out.println(body);
                        }

                    }
                }
            }
        }
    }
}
