package xyz.liangwh.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class MyAccessHandler extends ChannelInboundHandlerAdapter {

    private NioEventLoopGroup wg ;

    private MyReadHandler handler = new MyReadHandler();

    public MyAccessHandler(NioEventLoopGroup nwg){
        wg = nwg;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("serverRegistered");


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ChannelPipeline pipeline = ctx.pipeline();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       SocketChannel client = (SocketChannel) msg;
        System.out.println(client.localAddress());
        //注册
        wg.register(client);
        //handler
        client.pipeline().addLast(handler);


        //client.closeFuture().sync();
    }

}
