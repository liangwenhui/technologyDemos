package xyz.liangwh.algorithm.container.impl;

import java.util.Stack;

/**
 *
 * * 题3：
 *  * 如何用栈结构实现队列
 */
public class StackQueue {

    private Stack<String> s1 = new Stack();
    private Stack<String> s2 = new Stack();

    /**
     * 翻转
     */
    private void overturn(Stack source,Stack taget){
        if(source.isEmpty() || !taget.isEmpty()){
            throw  new RuntimeException("overturn失败，source.isEmpty()="+source.isEmpty()+"  !taget.isEmpty()="+!taget.isEmpty());
        }
        while (!source.isEmpty()){
            taget.push(source.pop());
        }

    }

    /**
     * put
     */
    public String put(String val){
        if(s1.isEmpty()&&!s2.isEmpty()){
            overturn(s2,s1);
        }
        s1.push(val);
        return val;
    }


    /**
     * pop
     */
    public String pop(){
        String res = null;
        if(s2.isEmpty()){
            overturn(s1,s2);
        }
        res = s2.pop();
        return res;
    }


    /**
     * peek
     */
    public String peek(){
        String res = null;
        if(s2.isEmpty()){
            overturn(s1,s2);
        }
        res = s2.peek();
        return res;
    }





}
