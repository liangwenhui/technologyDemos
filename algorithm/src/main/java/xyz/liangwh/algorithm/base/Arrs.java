package xyz.liangwh.algorithm.base;

import org.junit.Test;

import java.awt.*;
import java.util.Scanner;

public class Arrs {
    /**
     * 题目：
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的
     * 一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public static void main(String...args){
        //init
        int[] a = new int[]{1,4,6,8,9};
        int[] b = new int[]{11,13,14,15,16,17,20};
        int[] c = new int[]{44,90};
        int[][] arr = new int[][]{a,b,c};

        class Hander{
            public int[][] arr = null;
            public Hander (int[][] arr){
                this.arr = arr;
            }
            public Point loopSearch(int x){
                Point p = new Point(-1, -1);
                //确定所在列
                loop:
                    for(int i = 0;i<arr.length;i++){
                        int[] current = arr[i];
                        int head = current[0];
                        int tail = current[current.length-1];
                        if(head == x){
                            p.setLocation(i,0);
                            break;
                        }else if(tail == x){
                            p.setLocation(i,current.length-1);
                            break;
                        }else if(head<x && x<tail){
                            Integer index = recursionSearch(current,x,1,current.length-2);
                            if(index!=null){
                                p.setLocation(i,index.intValue());
                                break ;
                            }
                        }else{
                            continue;
                        }
                    }
                return p;
            }
            private Integer recursionSearch(int[] arr,int x,int first,int end){
                if(first>end){
                    return null;
                }else if(first == end){
                    if(x==arr[first]){
                        return first;
                    }
                }else{
                    int midIndex = first+((end-first)>>1);
                    if(midIndex>=first) {
                        Integer res = null;
                        int midc = Integer.compare(x, arr[midIndex]);
                        switch (midc) {
                            case 0:
                                return midIndex;
                            case 1:
                                res = recursionSearch(arr, x, midIndex + 1, end);
                                break;
                            case -1:
                                res = recursionSearch(arr, x, first, midIndex - 1);
                                break;
                        }
                        if (res != null) {
                            return res.intValue();
                        }
                    }
                }
                return null;
            }
        }
        System.out.println("input any num to test,but -1 to exit");
        Hander hander = new Hander(arr);
        Scanner scanner = new Scanner(System.in);
        int x = -1;
        while (scanner.hasNext()){
            x = scanner.nextInt();
            if(x==-1){
                break;
            }
            System.out.println(hander.loopSearch(x));
        }
        System.out.println("exit");

    }
}
