package xyz.liangwh.io.socket;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 单线程多路复用
 */
public class SocketMultiplexing {

    private ServerSocketChannel server = null;
    private Selector selector = null;
    int port = 9090;

    @SneakyThrows
    public void initServer(){
        server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(port));
        selector = Selector.open();
        /**
         * register() 方法的第二个参数。这是一个“ interest集合 ”，
         * 意思是在通过Selector监听Channel时对什么事件感兴趣。可以监听四种不同类型的事件：
         * Connect
         * Accept
         * Read
         * Write
         */
        server.register(selector, SelectionKey.OP_ACCEPT);
    }

    @SneakyThrows
    public void start(){
        initServer();
        while (true){
            Set<SelectionKey> keys = selector.keys();
            System.out.println(keys.size());

            while (selector.select(500)>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if(next.isAcceptable()){
                        acceptHandle(next);
                    }
                    if(next.isReadable()){
                        readHandle(next);
                    }
                    //为什么要remove？因为selector不会自己清除，下次遍历还会有重复数据
                    //同next.cancel();
                    iterator.remove();

                }
            }
        }
    }

    @SneakyThrows
    private void acceptHandle(SelectionKey key){
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel client = channel.accept();
        client.configureBlocking(false);
        ByteBuffer bb = ByteBuffer.allocate(1024);
        client.register(selector,SelectionKey.OP_READ,bb);
    }

    @SneakyThrows
    private void readHandle(SelectionKey key){
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer bb = (ByteBuffer) key.attachment();
        bb.clear();
        int read = 0;
        while(true){
            read = client.read(bb);
            if(read>0){
                bb.flip();
                while (bb.hasRemaining()){
                    client.write(bb);
                }
                bb.clear();
            }else  if(read==0){
                break;
            }else{
                client.close();
                break;
            }

        }
    }

}
