package xyz.liangwh.algorithm.base;

import java.util.Stack;

public class StackQueue {


    /**
     *  题目：
     * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
     */

    public static void main(String...args){

        SQueue<Integer> q = new SQueue();

        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        q.push(6);

        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());


    }


   static class SQueue<T>{
        private Stack<T> mainStack = new Stack();
        private Stack<T> tmpStack = new Stack();

        public void push(T item){
                mainStack.push(item);
        }

        public T pop(){
            flip(mainStack,tmpStack);
            T item = null;
            if(tmpStack.size()>0){
                 item = tmpStack.pop();
                flip(tmpStack,mainStack);
            }


            return item;
        }

        private void flip(Stack<T> source,Stack<T> target){
            int size = source.size()-1;
            for(int i=0;i<=size;i++){
                T item = source.pop();
                target.push(item);
            }
        }

    }


}
