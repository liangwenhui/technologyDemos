package xyz.liangwh.algorithm;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Tests {

/**
 *
 * & 与  同为1才为1
 * | 或  同为0才为0
 * ^ 异或 相同为0，不同为1 X -> 异或运算就是不进位相加
 * ~ 取反 0->1 ,1->0
 * << 左移
 * >> 右移
 * >>> 无符号右移
 *
 */

/**
 * 不用额外变量，交互两个数的值
 */
@Test
public void exChange(){
    int a = 10;
    int b=100;

    a = a ^ b;
    b = a ^ b ;
    a = a ^ b ;

    System.out.println("a = "+a);
    System.out.println("b = "+b);


}

/**
 * 有一个数组，有一个数字出现了偶数次，其他数字出现了奇数次，怎么找到奇数次的数字
 */
@Test
public  void findJi(){
    int[] arr = {1,1,1,1,1,1,2,2,3,3,3,3,4,4,4,4,4,4,4};
    int tag = 0;
    for(int item:arr){
        tag = tag ^item;
    }
    System.out.println(tag);

}

    /**
     * 有一个数组，有两个数字出现了偶数次，其他数字出现了奇数次，怎么找到奇数次的数字
     */
    @Test
    public  void findJi2(){
        int[] arr = {1,1,1,1,1,1,2,2,2,3,3,3,4,4,4,4,4,4};
        int tag = 0;
        for(int item:arr){
            tag = tag ^item;
        }

        //tag = a ^ b 两个不同的数字，异或一定不为0,必有一位是1
        //找到最后一位1
        int right =tag & (~tag+1);

        int once = 0;
        for(int item:arr){
            if((item & right) != 0){
                //tag 中 a和b分开
                once = once ^ item;
            }
        }

        System.out.println((tag^once) + "  "+ once);

    }

    @SneakyThrows
    @Test
    void testoe(){
        int[] c = {2,2};
        int[] e= {2,4};
        System.out.println(outputExpress(c,e));
    }

    @SneakyThrows
    private Map outputExpress(int[] c ,int[] e){
        if(e==null){throw new Exception();}
        StringBuffer exp = new StringBuffer();
        long res = 0;
        for(int i=0;i<e.length;i++){
            exp.append(c[i]).append("x").append(e[i]);
            exp.append("+");
            res += c[i]*e[i];
        }
        exp.delete(exp.length()-1,exp.length());
        Map map = new HashMap();
        map.put(exp.toString(),res);
        return map;

    }


}
