package xyz.liangwh.algorithm.recursive;

import org.junit.jupiter.api.Test;
import xyz.liangwh.algorithm.Utils.IntUtil;

import java.util.Arrays;

/**
 * 递归
 * 大事拆分为若干个小事，通过一个策略后，得到结果
 */
public class HelloRecursive {

    /**
     * 用递归得出数组某范围中的最大值
     */
    @Test
    void getMaxInArr(){
        int[] arr = IntUtil.generateRandomArray(20,200);
        System.out.println(Arrays.toString(arr));
        // 5-15 中的最大值
        int max = compareByIndex(0,5,15,arr);
        System.out.println(max);
    }

    private int compareByIndex(int max,int index,int maxIndex,int[] arr){
        if(max<arr[index]){
            max = arr[index];
        }
        if(index==maxIndex){
            return max;
        }
        return compareByIndex(max,++index,maxIndex,arr);
    }


    @Test
    void getMaxInArr2(){
        int[] arr = IntUtil.generateRandomArray(20,200);
        System.out.println(Arrays.toString(arr));
        // 5-15 中的最大值
        int max = compareBybreak(5,15,arr);
        System.out.println(max);
        max = compareByIndex(0,5,15,arr);
        System.out.println(max);
    }

    /**
     * 拆分
     * @param l
     * @param r
     * @param arr
     * @return
     */
    private int compareBybreak(int l ,int r,int[] arr){
        if(l == r){
            return arr[l];
        }
        int mid = l+((r-l)>>1);
        int lMax = compareBybreak(l,mid,arr);
        int rMax = compareBybreak(mid+1,r,arr);
        return Math.max(lMax,rMax);

    }

}
