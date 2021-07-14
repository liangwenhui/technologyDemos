package xyz.liangwh.algorithm.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RLinked {


    /**
     *  题目：
     * 输入一个链表，从尾到头打印链表每个节点的值。
     */

    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);

        Stack<Integer> stack = new Stack<>();
        while (!l.isEmpty()){
            int x = l.poll();
            stack.push(x);
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}
