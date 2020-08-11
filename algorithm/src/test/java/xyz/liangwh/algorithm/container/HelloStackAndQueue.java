package xyz.liangwh.algorithm.container;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import xyz.liangwh.algorithm.container.impl.MarkStack;
import xyz.liangwh.algorithm.container.impl.QueueStack;
import xyz.liangwh.algorithm.container.impl.RoundQueue;
import xyz.liangwh.algorithm.container.impl.StackQueue;

import java.util.Stack;

/**
 * 栈  ： 先进后出  （子弹与弹夹）
 * 队列 ： 先进先出 （珠子与管道）
 *
 * 栈和队列 只是逻辑数据类型
 * 具体实现可以用 双向链表 数组实现
 *
 * 题1：
 * 使用数组实现队列 ---循环数组 round buffer
 *      两个指针，一个指向当前头节点
 *      一个指向存放节点。两种思路：
 *          1.判断存放节点是否追赶上头节点
 *          2.增加size变量，记录元素值，如果size!=0 认为有元素可取，如果size!=max，认为队列未满
 *
 * 题2：
 * 实现一个栈，在基础功能，增加一个功能，获取栈中最小值。
 * 要求时间复杂度O(1)
 *  实现要点：空间换时间，两个栈，一个存数据dataStack，一个存最小值minStack（版本）
 *          新数字pull进来，与minStack栈顶对比，小则同时压2个栈，大则压data栈，吧min栈顶再压一个进min栈
 *          保证两个栈同高度，类似版本控制，永远记录着不同时刻，栈的最小值
 *
 *
 * 题3：
 * 如何用栈结构实现队列
 *（第一反应，2个栈，地一个栈元素弹出到第二个栈，第二个栈输出，再倒回去（2个水杯的感觉））
 *
 * 题4：
 * 如何是队列实现栈结构
 *（2个队列，也是倒水一样，但是要倒剩下一个，给用户返回）
 */
public class HelloStackAndQueue {

    @Test
    @SneakyThrows
    void  testMyQueue(){
        RoundQueue<String> killQueue = new RoundQueue<>(6);
        killQueue.add("1");
        killQueue.add("2");
        killQueue.add("3");
        System.out.println(killQueue.showElemnt());
        System.out.println(killQueue.poll());
        System.out.println(killQueue.showElemnt());
        killQueue.add("4");
        killQueue.add("5");
        killQueue.add("6");
        killQueue.add("7");
        System.out.println(killQueue.poll());
        System.out.println(killQueue.showElemnt());
        killQueue.add("8");
        System.out.println(killQueue.showElemnt());
    }

    @Test
    @SneakyThrows
    void testStack(){
        MarkStack stack = new MarkStack();
        stack.push(5);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(9);
        System.out.println(stack.getMin());
        System.out.println("pop:"+stack.pop());
        System.out.println(stack.getMin());
        System.out.println("pop:"+stack.pop());
        System.out.println(stack.getMin());
        System.out.println("pop:"+stack.pop());
        System.out.println(stack.getMin());
        System.out.println("pop:"+stack.pop());
        System.out.println(stack.getMin());
        System.out.println("pop:"+stack.pop());
        System.out.println(stack.getMin());
    }

    @Test
    @SneakyThrows
    void testStackQueue(){
        StackQueue queue = new StackQueue();
        queue.put("l");
        queue.put("i");
        queue.put("a");
        queue.put("n");
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.put("g");
        queue.put("w");
        queue.put("h");
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    @SneakyThrows
    @Test
    void testQueueStack(){
        QueueStack stack = new QueueStack();
        stack.pust(1);
        stack.pust(2);
        stack.pust(3);
        stack.pust(4);
        stack.pust(5);
        System.out.println(stack.peek());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        stack.pust(6);
        stack.pust(7);
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        //5 5 4 7 6 3 2 1

    }


}
