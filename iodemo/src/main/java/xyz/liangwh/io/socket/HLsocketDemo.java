package xyz.liangwh.io.socket;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket 相关配置
 * BIO throw threads
 */
public class HLsocketDemo {
    /**
     * server config
     */
    //服务起来后，许多客户端要连接进来，BACK LOG设置能够hold住的数量，其他多余客户端直接拒绝
    private static int BACK_LOG = 3;
    //接受缓冲区大小 默认 8192B  8K
    private static int RECEIVE_BUFFER_SIZE=1024;
    //accept 阻塞超时
    private static int SO_TIMEOUT = 1000;
    //是否允许socket端口重用，很少设置为true
    private static boolean REUSE_ADDRESS=false;

    /**
     * client config
     */
    //是否长连接
    private static boolean KEEPALIVE = false;
    //是否试探发送首个字节
    private static boolean OOB =false;
    //客户端接收buffer大小
    private static int CLI_REC_BUFFER_SIZE =100;
    private static boolean CLI_REUSE_ADDRESS = false;
    //客户端发送缓冲区大小
    private static int CLI_SEND_BUFFER_SIZE = 120;
    //是否立即销毁连接资源
    private static boolean LINGER = true;
    //断开速度
    private static int LINGER_TIME =0;
    //读取超时
    private static int CLI_TIMEOUT=0;
    //是否使用TCP缓冲优化算法，多次合并一次，数据体量大才考虑使用
    private static boolean NO_DELAY = false;

    public static void main(String[] args) throws Exception{
        ServerSocket server = null;
        try {
            //------------------
            //server = new ServerSocket(8081,BACK_LOG);
            server = new ServerSocket();
            server.bind(new InetSocketAddress(8081),BACK_LOG);
            server.setReceiveBufferSize(RECEIVE_BUFFER_SIZE);
            server.setSoTimeout(SO_TIMEOUT);
            server.setReuseAddress(REUSE_ADDRESS);
            //------------------
            System.out.println("socket open in "+8081);
            while (true){
                try {
                    System.in.read();
                    Socket client = server.accept();
                    client.setKeepAlive(KEEPALIVE);
                    client.setOOBInline(OOB);
                    client.setReceiveBufferSize(CLI_REC_BUFFER_SIZE);
                    client.setSendBufferSize(CLI_SEND_BUFFER_SIZE);
                    client.setSoLinger(LINGER,LINGER_TIME);
                    client.setSoTimeout(CLI_TIMEOUT);
                    client.setTcpNoDelay(NO_DELAY);

                    new Thread(()->{
                        while (true){
                            InputStream in = null;
                            try {
                                in = client.getInputStream();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                          char[] data = new char[1024];
                            int num = 0;
                            try {
                                num = reader.read(data);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if(num>0){
                              System.out.println("data length:"+num+",val="+new String(data));
                          }else if(num==0){
                              System.out.println("client readed nothing!");
                              continue;
                          }else {
                              System.out.println("client close?");
                                try {
                                    client.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                          }
                        }
                    }).start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            server.close();
        }
    }
}
