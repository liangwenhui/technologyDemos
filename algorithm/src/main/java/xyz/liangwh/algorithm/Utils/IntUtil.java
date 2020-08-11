package xyz.liangwh.algorithm.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IntUtil {
    /**
     * int 转 32位 二进制字符串
     * @param x
     * @return
     */
    public static String intTo32BitBinary(int x) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            sb.append(x & 1);
            //无符号右移，高位补0，包括符号位在内一起右移
            //>> 是有符号右移。符号位保持不变，负数高位补1，正数高位补0。
            x = x >>> 1;
        }
        //得到32位的二进制字符串
        String str32 = sb.reverse().toString();
        return str32;
    }

    /**
     * 全正数随机数组
     * Math.random() [0,1)
     * Math.random() * N [0,N)
     * (int)Math.random() * N [0,N-1]
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr = new int[(int) (Math.random()*(maxSize)+1)];
        for(int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*(maxValue)+1);
        }
        return arr;
    }

    /**
     * 全正数不重复随机数组
     * Math.random() [0,1)
     * Math.random() * N [0,N)
     * (int)Math.random() * N [0,N-1]
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static ArrayList<Integer> generateRandomNotCompArray(int maxSize,int maxValue){
        int length = maxSize;
        ArrayList<Integer> list = new ArrayList();
        int item = -1;

        for(int i=0;i<length;i++){
            while(true){
                item = (int) (Math.random()*(maxValue)+1);
                if(!list.contains(item)){
                    list.add(item);
                }
                break;
            }

        }

        return list;

    }

}
