package xyz.liangwh.algorithm.search;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import xyz.liangwh.algorithm.Utils.IntUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class HalfSearch {


    public static void main(String[] args) {
//        int[] arr = IntUtil.generateRandomNotCompArray(10,1000);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 二分查找法
     */
    @Test
    @SneakyThrows
    public void halfSearch(){
        int tag = 45;
        int[] nums = {1,23,34,45,52,64,75,86,91,101,114,125,138,145,158,163,177,182,196};
        //开始下标
        int start = 0;
        //结束下标
        int end = nums.length-1;
        //中间下标
        int index = 0;
        //循环次数
        int count=0;
        while(start<=end){
            count++;
            //两数相加，可能会溢出(start+end)>>1;
            index = start +((end-start)>>1);
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
            System.out.println("找不到,循环了"+count+"次");
        }

    }

    /**
     * 二分查找 比x大的第一个数字
     */
    @Test
    @SneakyThrows
    public void halfSearch2(){
        int tag = 10;
        int[] nums = {1,2,2,2,3,3,5,6,6,6,6,6,7,7,7,7,7,8,8,8,8,9};
        //开始下标
        int start = 0;
        //结束下标
        int end = nums.length-1;
        //中间下标
        int index = 0;
        //循环次数
        int count=0;
        int match = 0;
        int matchIndex = 0;
        while(start<=end){
            count++;
            index = start +((end-start)>>1);
            if(nums[index]>tag){
                match = nums[index];
                matchIndex = index;
                end = index-1;
            }else {
                start = index+1;
            }
        }
        if(match>tag&&match!=0){
            System.out.println("数据match="+match+"在数据下标"+matchIndex+" 值["+nums[matchIndex]+"],循环了"+count+"次");
        }else {
            System.out.println(match);
            System.out.println("找不到,循环了"+count+"次");
        }
    }


    /**
     * 一个无序不重复集合 局部最小 --- 与紧挨着的相邻数据对比，最小的称为局部最小
     * 先判断 0 1位置 0是否比1小，是则0位置局部最小
     * 判断 n-1 n位置 n是否比n-1小，是则 n位置局部最小
     * 都不是则
     * \                         /
     *  \ ....................../
     *  中间某一位置，一定存在局部最小
     */
    @Test
    public void getOnceLessNum(){
        ArrayList<Integer> arr = IntUtil.generateRandomNotCompArray(20,1000);
        System.out.println(arr);
        int start = 0;
        int end = arr.size()-1;
        int mid = -1;

        if(arr.get(0)<arr.get(1)){
            System.out.println("首位出现局部最小,值="+arr.get(0));
        }else if(arr.get(end)<arr.get(end-1)){
            System.out.println("尾部出现局部最小,值="+arr.get(end));
        }else {
            while(start<=end){

                mid = start+((end-start)>>1);
                if(arr.get(mid)>arr.get(mid-1)){
                    end = mid-2;
                }else if(arr.get(mid)>arr.get(mid+1)){
                    start = mid+2;
                }else {
                    System.out.println("找到局部最小，位于"+mid+",值="+arr.get(mid));
                    break;
                }

            }
        }






    }

}
