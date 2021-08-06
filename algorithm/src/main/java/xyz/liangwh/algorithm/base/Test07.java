package xyz.liangwh.algorithm.base;

import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test07 {


    /**
     *  题目：
     *
     * 大家都知道斐波那契数列，现在要求输入一个整数 n，请你输出斐波那契数列的第 n 项。n<=39
     *
     * F(N),F(N-1) = F(1),F(2) X [1,1][1,0]^(n-2)
     * F(N) = F(1) X [1,1][1,0]^(n-2)
     * F(1) = 1
     * 求一个数字的N次幂，通过快速幂来计算
     */

    public static void main(String[] args) {
        for(int n=1;n<10;n++){
            System.out.println(f(n));
        }
        //System.out.println(f(6));
//        int [][] a = {{1,0},{0,1}};
//        int [][] b = {{1,1},{1,0}};
//
//        int [][] c = getMatrixCounting2(b,a);
//        System.out.println(c);
    }

    /**
     * 获取第N项 N从1开始
     * @param n
     * @return
     */
    public static int f(int n){
        int res = 1;
        if(n<3){
            return res;
        }
        String bitarr = Integer.toBinaryString(n - 2);
        Stack<Character> bitStack = new Stack();
        for(int i=0;i<bitarr.length();i++){
            bitStack.push(bitarr.charAt(i));
        }
        bitarr = null;
        int[][] matrix = {
                {1,1},
                {1,0}
        };
        int [][] tmp = {{1,0},{0,1}};
        res = getMatrixCounting(bitStack,matrix,tmp,new ArrayList<int[][]>());


        return res;
    }


    private static int getMatrixCounting(Stack<Character> bitStack, int [][] matrix, int[][] res, List<int[][]> cunnent){
        int [][] var = {{0,0},{0,0}};
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res[i].length;j++){
                for(int z=0;z<matrix.length;z++){
                    int y = matrix[z][j];
                    int a = res[i][z];
                    var[i][j] +=y*a;
                }
            }
        }
        res = var;
        if(bitStack.pop()==49){
            cunnent.add(res);
        }

        if(bitStack.isEmpty()){
            //计算
            int mi = 0;
            int [][] tmp = {{1,0},{0,1}};
            for(int i=0;i<cunnent.size();i++){
                tmp = getMatrixCounting2(cunnent.get(i),tmp);
            }
            for(int i=0;i<tmp.length;i++){
                int y = tmp[i][0];
                mi+=y;
            }
            return mi;
        }
        return getMatrixCounting(bitStack,res,res,cunnent);
    }

    private static int[][] getMatrixCounting2( int [][] matrix, int[][] res){

        int [][] var = {{0,0},{0,0}};
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res[i].length;j++){
                for(int z=0;z<matrix.length;z++){
                    int y = matrix[z][j];
                    int a = res[i][z];
                    var[i][j] +=y*a;
                }
            }
        }


        return var;

    }










}
