package xyz.liangwh.algorithm.base;

import java.util.Stack;

public class OneStack {

    /**
     * 一个栈，不用其他额外的数据结构，怎么将他翻转
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        filp(stack,stack.size());
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    private static void filp(Stack<Integer> stack,int size){
        int lster = getLster(stack);
        if(size>1){
            filp(stack,size-1);
        }
        stack.push(lster);
    }

    private static int getLster(Stack<Integer> stack){
            Integer pop = stack.pop();
            if (stack.isEmpty()){
                return pop;
            }else{
                int lster = getLster(stack);
                stack.push(pop);
                return lster;
            }

    }



}
