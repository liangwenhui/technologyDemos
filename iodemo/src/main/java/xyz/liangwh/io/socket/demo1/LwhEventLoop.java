package xyz.liangwh.io.socket.demo1;

import lombok.Data;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

@Data
public class LwhEventLoop implements Executor {

    private String name;
    private Selector selector ;


    public  LwhEventLoop(String name) throws IOException {
        this.name = name;
        selector = selector.open();
    }


    public  void loop() throws IOException {
        while (true){
            //阻塞等待
            int i = selector.select();
            if(i>0){
                //获取关注的事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()){

                    }else if(key.isReadable()){

                    }
                }
            }
        }
    }

    @Override
    public void execute(Runnable command) {

    }
}
