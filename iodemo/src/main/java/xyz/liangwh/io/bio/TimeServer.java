package xyz.liangwh.io.bio;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServer {
    public static int  port = 8080;
    /**
     * 使用ServerSocket 创建服务器socket ，将
     * 客户端连接 accept之后，获得socket对象，将socket对象
     * 新建线程进行读写。
     * @throws IOException
     */
    @Test
    public  void newThread() throws IOException {


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

    /**
     * 通过线程池减少线程重复创建的性能消耗，伪异步IO
     * 对于socket的accpet 和 读写操作，都是会阻塞的
     */
    @Test
    public void executor(){
        ServerSocket server = null;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(16), new ThreadFactory() {
            private long index = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "TIME_SERVER_THREAD_" + index++);
            }
        }, new ThreadPoolExecutor.AbortPolicy());

        try{
            server = new ServerSocket(port);
            Socket client = null;
            while (true){
                client = server.accept();
                threadPoolExecutor.execute(new TimeClientHandler(client));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
