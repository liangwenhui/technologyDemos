package xyz.liangwh.algorithm;


import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

public class Tests {

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

    public static void main(String[] args) {
        //System.out.println((29+3)/2);
        //System.out.println(Arrays.toString(generateRandomArray(100,100)));
    }


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

    @Test
    public void youyi(){

        int a = -123;
        System.out.println(intTo32BitBinary(a));
        a = a >> 2;
        System.out.println(intTo32BitBinary(a));
    }

    /**
     * 二分查找法
     */
    @Test
    public void halfSearch(){
        int tag = 114;
        int[] nums = {1,23,34,45,52,64,75,86,91,101,114,125,138,145,158,163,177,182,196};
        //开始下标
        int start = 0;
        //结束下标
        int end = nums.length-1;
        //中间下标
        int index = 0;
        //循环次数
        int count=0;
       // int [] here = null;
        while(start<=end){
            count++;
            index = (start+end)/2;
            if(nums[index]==tag){
                break;
            }else if(nums[index]>tag){
                end = index-1;
            }else {
                start = index+1;
            }
        }
        if(tag==nums[index]){
            System.out.println("数据tag="+tag+"在数据下标"+index+" 值["+nums[index]+"],循环了"+count+"次");
        }else {
            System.out.println(nums[index]);
            System.out.println("找不到");
        }

    }
}
