package xyz.liangwh.algorithm.container.impl;

import java.util.ArrayList;

/**
 *  * 题1：
 *  * 使用数组实现队列 ---循环数组 round buffer
 *  *      两个指针，一个指向当前头节点
 *  *      一个指向存放节点。两种思路：
 *  *          1.判断存放节点是否追赶上头节点
 *  *          2.增加size变量，记录元素值，如果size!=0 认为有元素可取，如果size!=max，认为队列未满
 */
public class RoundQueue<T> {

    private T[] arr;
    private int size=0;
    private int maxSize=0;
    //当前头部
    private int headIndex=0;
    //当前数据应插入位置
    private int inputIndex=0;

    private RoundQueue(){}

    public RoundQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = (T[]) new Object[maxSize];
    }

    /**
     * 获得下一个插入位置
     * @return
     */
    private void  getNextInputIndex(){
        inputIndex++;
        if(inputIndex>(maxSize-1)){
            inputIndex -= maxSize;
        }
    }
    /**
     * 获得下一个位置
     * @return
     */
    private int  getNextIndex(int index){
        index++;
        if(index>(maxSize-1)){
            index -= maxSize;
        }
        return index;
    }
    /**
     * add(value)
     */
    public void add(T value) throws Exception{
        if(size==maxSize){
            throw new RuntimeException("There is no place to store it");
        }
        arr[inputIndex] = value;
        getNextInputIndex();
        size++;
    }

    /**
     * poll(index) get and remove the head element
     */
    public T poll(){
        if(size==0){
            throw new RuntimeException("the head is empty");
        }
        T result = arr[headIndex];
        headIndex++;
        size--;
        return result;
    }



    /**
     * peek(index)
     */
    public T peek(){
        if(size==0){
            throw new RuntimeException("the head is empty");
        }
        T result = arr[headIndex];
        return result;
    }

    public String showElemnt(){
        StringBuffer sb = new StringBuffer();
        int index = headIndex;
        for(int count=0;count<size;count++){
            sb.append(arr[index])
                    .append(",");
            index = getNextIndex(index);
        }
        return sb.toString().substring(0,sb.length()-1);
    }




}
