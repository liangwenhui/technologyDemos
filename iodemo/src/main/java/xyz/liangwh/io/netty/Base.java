package xyz.liangwh.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * channel bytebuffer selector
 * bytebuf
 */
public class Base {

    @Test
    public void byteBuf(){
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(8, 20);
        printVar(buffer);
        buffer.writeBytes(new byte[]{1,2,3,4});
        printVar(buffer);

        buffer.writeBytes(new byte[]{1,2,3,4});
        printVar(buffer);

        buffer.writeBytes(new byte[]{1,2,3,4});
        printVar(buffer);

        buffer.writeBytes(new byte[]{1,2,3,4});
        printVar(buffer);

        buffer.writeBytes(new byte[]{1,2,3,4});
        printVar(buffer);

        buffer.writeBytes(new byte[]{1});
        printVar(buffer);
    }

    @Test
    public void server() throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        NioEventLoopGroup clientGroup = new NioEventLoopGroup(2);
        NioServerSocketChannel server = new NioServerSocketChannel();
        eventExecutors.register(server);
        server.bind(new InetSocketAddress(9090));

        ChannelPipeline pipeline = server.pipeline();
        pipeline.addLast(new MyAccessHandler(clientGroup));//接受客户端，并且把client完成注册

        server.closeFuture().sync();
    }

    @SneakyThrows
    @Test
    public void nittyServer(){
        NioEventLoopGroup boos = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(1);
        ChannelFuture bind = new ServerBootstrap()
                .group(boos)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MyReadHandler());
                        ch.pipeline().remove(this);
                    }
                }).bind(9009);
        bind.sync();
        bind.channel().closeFuture().sync();
    }

    @Test
    @SneakyThrows
    public void nittyClient(){
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        ChannelFuture connect = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MyReadHandler());
                        ch.pipeline().remove(this);
                    }
                }).connect(new InetSocketAddress("134.175.130.172",9090));
        connect.sync();
        connect.channel().closeFuture().sync();

    }

    @Test
    public void client() throws IOException, InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
//        eventExecutors.execute(()->{
//            System.out.println(Thread.currentThread()+"_ hello");
//        });
//reactor 异步特征，netty几乎所有操作都是异步的
        NioSocketChannel channel = new NioSocketChannel();
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new MyReadHandler());
        eventExecutors.register(channel);
       // ChannelFuture connect = channel.connect(new InetSocketAddress("134.175.130.172", 9009));
        ChannelFuture connect = channel.connect(new InetSocketAddress( 9090));
        ChannelFuture sync = connect.sync();
        //写数据
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello nitty".getBytes());
        ChannelFuture channelFuture = channel.writeAndFlush(byteBuf);
        channelFuture.sync();


        //等待其中一方断开连接
        sync.channel().closeFuture().sync();

    }
    
    
    private void printVar(ByteBuf buffer){
        System.out.println("isReadable "+buffer.isReadable());
        System.out.println("readerIndex "+buffer.readerIndex());
        System.out.println("readableBytes "+buffer.readableBytes());
        System.out.println("isWritable "+buffer.isWritable());
        System.out.println("writerIndex "+buffer.writerIndex());
        System.out.println("isDirect "+buffer.isDirect());
        System.out.println("capacity "+buffer.capacity());
        System.out.println("maxCapacity "+buffer.maxCapacity());
        System.out.println("---------------------------------------------");
    }



}




