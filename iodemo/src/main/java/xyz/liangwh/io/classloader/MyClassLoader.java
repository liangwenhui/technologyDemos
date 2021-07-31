package xyz.liangwh.io.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader{

    public MyClassLoader(){}
    public MyClassLoader(ClassLoader parent){
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File classFile = new File("D:\\Person.class");
        try{
            byte[] body = getClassFileBytes(classFile);
            Class<?> aClass = this.defineClass(name, body, 0, body.length);
            return aClass;
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassFileBytes(File classFile) throws Exception {
        FileInputStream in = new FileInputStream(classFile);
        FileChannel channel = in.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer bb = ByteBuffer.allocate(1024);

        while (true){
            int read = channel.read(bb);
            if (read <=0){
                break;
            }
            bb.flip();
            wbc.write(bb);
            bb.clear();
        }
        in.close();
        return baos.toByteArray();




    }

}
