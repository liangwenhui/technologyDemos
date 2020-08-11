package xyz.liangwh.algorithm.container.impl;

import java.util.Stack;

/**
 * * 题2：
 *  * 实现一个栈，在基础功能，增加一个功能，获取栈中最小值。
 *  * 要求时间复杂度O(1)
 *  *  实现要点：空间换时间，两个栈，一个存数据dataStack，一个存最小值minStack（版本）
 *  *          新数字pull进来，与minStack栈顶对比，小则同时压2个栈，大则压data栈，吧min栈顶再压一个进min栈
 *  *          保证两个栈同高度，类似版本控制，永远记录着不同时刻，栈的最小值
 */
public class MarkStack {

    // 记录数据
    private Stack dataStack;
    //记录最小值
    private Stack markStack;

    public MarkStack(){
        dataStack = new Stack();
        markStack = new Stack();
    }

    /**
     * 对比插入
     * @param val
     */
    private void markAndSet(int val){
        if(markStack.size()==0){
            markStack.push(val);
            return;
        }
        int min = (int) markStack.peek();
        if(val<min){
            markStack.push(val);
        }else{
            markStack.push(min);
        }
    }

    /**
     * 压栈
     * @param val
     * @return
     */
    public int push(int val){
        dataStack.push(val);
        markAndSet(val);
        return val;
    }

    /**
     * 弹出
     * @return
     */
    public int pop(){
        if (!dataStack.isEmpty()){
            markStack.pop();
            return (int)dataStack.pop();
        }else {
            throw new RuntimeException("stack is empty!");
        }
    }

    public int peek(){
        return (int)dataStack.peek();
    }

    /**
     * 查看当前最小值
     * @return
     */
    public int getMin(){
        return  (int)markStack.peek();
    }

}
