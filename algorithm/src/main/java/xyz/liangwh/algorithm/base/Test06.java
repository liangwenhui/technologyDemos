package xyz.liangwh.algorithm.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Test06 {



    /**
     * 题目：
     *
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的
     * 最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为 1。NOTE：给出的所有元素都大于 0，若数组大
     * 小为 0，请返回 0。
     */
    @Test
    public void findIndexInFilpArr(){
        int[] arr = {3,4,5,6,7,8,9,1,2};

        int[] tmp = Arrays.copyOf(arr,arr.length);
//        int mid = Math.round(arr.length/2f);
        int mid = tmp.length>>1;
        int max = tmp.length-1;
        int min = 0;
        while (true){
            int m = tmp[mid];
            if(mid>0){
                int l = tmp[mid-1];
                if(l>m){
                    min = m;
                    break;
                }
            }
            if(mid<max){
                int r = tmp[mid+1];
                if(r<m){
                    min = r;
                    break;
                }
            }
            int[] left = Arrays.copyOfRange(tmp,0,mid);
            int[] right = Arrays.copyOfRange(tmp,mid+1,tmp.length);
            int lm = left[0];
            int rm = right[0];
            if(lm<rm){
                tmp = right;
            }else{
                tmp = left;
            }
            mid = tmp.length>>1;
             max = tmp.length-1;
        }
        System.out.println(min);

    }
}
