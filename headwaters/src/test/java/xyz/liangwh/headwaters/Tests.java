package xyz.liangwh.headwaters;


import org.junit.Test;

public class Tests {


    @Test
    public void test1(){
        long i = 1;
        long id = 1000;

        System.out.println((i<<32) | id);
    }

    @Test
    public void test2(){
        int i = 1;

        System.out.println(i<<2);
        System.out.println(i);

    }
}
