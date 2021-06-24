package xyz.liangwh.io.socket;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * 只要四元组唯一，则能建立socket，建立tcp
 */
public class SocketChannelDemo {
@SneakyThrows
    public static void main(String[] args) {
        SocketChannel channel = SocketChannel.open();
        channel.bind(new InetSocketAddress("127.0.0.1",80));
        //与远程socket建立链接
        channel.connect(new InetSocketAddress("192.10.20.110",9090));
    }
}
