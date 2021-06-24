package xyz.liangwh.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.SneakyThrows;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class FileZeroCopy {

    @SneakyThrows
    public static void main(String[] args) {
        RandomAccessFile raf = new RandomAccessFile("E:\\fileid.txt", "r");

        FileChannel rafchannel = raf.getChannel();
        SocketAddress address;
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("134.175.130.172",9090));
        rafchannel.transferTo(0,rafchannel.size(),socketChannel);
        socketChannel.close();
        rafchannel.close();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup(1),new NioEventLoopGroup(2));
    }
}
