package xyz.liangwh.algorithm.container.impl;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * * 题4：
 *  * 如何是队列实现栈结构
 *  *（2个队列，也是倒水一样，但是要倒剩下一个，给用户返回）
 */
public class QueueStack {

    private LinkedBlockingQueue q1 = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue q2 = new LinkedBlockingQueue<>();

    private LinkedBlockingQueue current = q1;

    private LinkedBlockingQueue getEmptyQueue(){
        if(current==q1){
            return q2;
        }else{
            return q1;
        }
    }

    private int checkCurrent(boolean pop){
        int res = -1;
        LinkedBlockingQueue q = getEmptyQueue();
        while (current.size()!=1){
            q.add(current.poll());
        }
        res = (int)current.poll();
        if(!pop){
            q.add(res);
        }
        current = q;
        return res;
    }

    /**
     * pust
     */
    public int pust(int val){
        current.add(val);
        return val;
    }
    /**
     * poll
     */
    public int poll(){
        if (current.isEmpty()){
            throw new RuntimeException("stack is empty!");
        }
        return checkCurrent(true);
    }

    /**
     * peek
     */
    public int peek(){
        if (current.isEmpty()){
            throw new RuntimeException("stack is empty!");
        }
        return checkCurrent(false);
    }



}
