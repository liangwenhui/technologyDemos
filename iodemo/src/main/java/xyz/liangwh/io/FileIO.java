package xyz.liangwh.io;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileIO {

    private String fileName = "E:\\fileid.txt";
    private String data = "12345678\n";

    /**
     * 基本io
     * 每次write都是一次系统调用
     */
    @SneakyThrows
    @Test
    public void testBasicFileIO(){
        File file  = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for(int i=0;i<100;i++){
            Thread.sleep(10);
            fileOutputStream.write(data.getBytes());
        }
    }

    /**
     * Buffer io
     * 也是调用write，但是一批数据调用一次系统调用
     */
    @SneakyThrows
    @Test
    public void testBufferFileIO(){
        File file  = new File(fileName);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        for(int i=0;i<100;i++){
            Thread.sleep(10);
            out.write(data.getBytes());
        }
    }

    /**
     * nio- new io
     * seek
     * channel
     *
     */
    @SneakyThrows
    @Test
    public void testNio(){
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");

        raf.write("hello 123\n".getBytes());
        raf.write("hello 456\n".getBytes());
        System.out.println("write------------");
        //System.in.read();

        raf.seek(4);
        raf.write("ooxx".getBytes());

        System.out.println("seek---------");
        //System.in.read();

        FileChannel rafchannel = raf.getChannel();
        //只有filechannel才有map，会使用mmap使用堆外内存和文件映射
        MappedByteBuffer map = rafchannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        //应该是mmap文件映射，调用put不是系统调用，他是直接写到page cache中
        map.put("@@@".getBytes());
        System.out.println("map--put--------");
        //System.in.read();
//        map.force(); //  flush
        raf.seek(2);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        int read = rafchannel.read(buffer);
        System.out.println(buffer);
        buffer.flip();
        System.out.println(buffer);
        for (int i = 0; i < buffer.limit(); i++) {
            Thread.sleep(200);
            System.out.print(((char)buffer.get(i)));
        }

    }


    @Test
    public  void whatByteBuffer(){
        //堆内内存
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //堆外内存
        //ByteBuffer buffer =ByteBuffer.allocateDirect(1024);

        System.out.println("postition: " + buffer.position());//指针
        System.out.println("limit: " +  buffer.limit());//指针最大位置
        System.out.println("capacity: " + buffer.capacity());//buffer 总大小
        System.out.println("mark: " + buffer);

        buffer.put("123".getBytes());

        System.out.println("-------------put:123......");
        System.out.println("mark: " + buffer);
        //翻转  读写行为交替调用  limit->指针 指针->0
        //一般是 写入状态->读取状态
        buffer.flip();

        System.out.println("-------------flip......");
        System.out.println("mark: " + buffer);

        byte b = buffer.get();

        System.out.println("-------------get......");
        System.out.println("mark: " + buffer);
        //buffer 挤压，limit->cap 指针->可写位置
        //一般是 读取状态->写入状态
        buffer.compact();

        System.out.println("-------------compact......");
        System.out.println("mark: " + buffer);

        buffer.clear();

        System.out.println("-------------clear......");
        System.out.println("mark: " + buffer);

    }
}
