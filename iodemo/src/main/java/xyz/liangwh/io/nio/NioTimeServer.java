package xyz.liangwh.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 对应 socket 和 serverSocket
 * NIO有 SocketChannel 和 ServerSocketChannel
 *
 * BIO读写是面向 流的，而NIO读写面向buffer
 * java为我们提供了除了boolean之外的基本类型的buffer类
 * @See ByteBuffer
 * @See CharBuffer
 * @See ShortBuffer
 * @See IntBuffer
 * @See LongBuffer
 * @See FloatBuffer
 * @See DoubleBuffer
 *
 * channel
 * selector ： channel 注册到 selector上，selector不断轮询channel，将就绪的channel拿出来读写
 * 通过selectionKey 获得  就绪channel集合
 *
 */
public class NioTimeServer {


    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress("localhost", 8080),1024);
        server.configureBlocking(false);
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            TimeUnit.SECONDS.sleep(1);
            selector.select(100);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey next = null;
            while (iterator.hasNext()){
                next = iterator.next();
                iterator.remove();
                if(next.isValid()){
                    if (next.isAcceptable()){
                        ServerSocketChannel ssc = (ServerSocketChannel)next.channel();
                        SocketChannel client = ssc.accept();
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_READ);
                        System.out.println("一个客户端连接进来");
                    }else if(next.isReadable()){
                        SocketChannel sc = (SocketChannel)next.channel();
                        System.out.println("一个客户端写入数据");
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(readBuffer);
                        if(readBytes>0){
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            String body = new String(bytes,"UTF-8");
                            String currentTime = null;
                            body = body.trim();
                            System.out.println("timeServer recevie client body:"+body);
                            if("QUERY_TIME".equals(body)){
                                currentTime = new Date().toString();
                                System.out.println(currentTime);
                                readBuffer.clear();
                                readBuffer.put(currentTime.getBytes());
                                readBuffer.flip();
                                sc.write(readBuffer);
                            }else if("EXIT".equals(body)){
                                next.cancel();
                                sc.close();
                            }else if(body.equals("")){
                                next.cancel();
                                sc.close();
                            }

                        }else if(readBytes<0){
                            //没有数据写入的时候，要有这一步，因为客户端断开连接后，一直处于可读状态
                            next.cancel();
                            sc.close();
                        }


                    }

                }
            }
        }
        

    }

}
