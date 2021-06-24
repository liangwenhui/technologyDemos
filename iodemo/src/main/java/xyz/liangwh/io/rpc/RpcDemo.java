package xyz.liangwh.io.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * RPC api入侵
 * 1.通信，连接数，拆包
 * 2.动态代理，序列号，协议封装
 * 3.连接池
 */
public class RpcDemo {
    @Test
    public void test(){
        Foo foo = getProxy(Foo.class);
        Boo boo = getProxy(Boo.class);
        foo.fun("hello");
    }

    private <T>T getProxy(Class<T> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        return (T) Proxy.newProxyInstance(
                classLoader,
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /**
                         *rpc消费逻辑
                         *1.消息
                         * 2.request message 缓存
                         * 3.连接池，获取远端地址
                         * 4.发送数据
                         * 5.接受数据
                         */

                        String serviceName =  clazz.getSimpleName();
                        String methodName = method.getName();

                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        ObjectOutputStream oout = new ObjectOutputStream(out);
                        oout.writeObject(new Object());
                        byte[] bytes = out.toByteArray();
                        return null;
                    }
                }
        );
    }
}




interface Foo{

    void fun(String s);
}
interface  Boo{
    void fun(String s);
}