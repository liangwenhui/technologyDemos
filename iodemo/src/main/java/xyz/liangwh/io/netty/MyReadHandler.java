package xyz.liangwh.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * implements ChannelHandler, EventExecutorGroup
 */
//配置sharable handler共享 最好是新增一个专门分配handler的handler
@ChannelHandler.Sharable
public class MyReadHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       ByteBuf byteBuf = (ByteBuf)msg;
        //read 会移动read指针 ，get不会，使用get才能重复读bytebuf
        CharSequence charSequence = byteBuf.getCharSequence(byteBuf.readerIndex(),byteBuf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(charSequence);

        ctx.writeAndFlush(msg);
    }


}
