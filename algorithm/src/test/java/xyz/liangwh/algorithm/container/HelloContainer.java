package xyz.liangwh.algorithm.container;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * 链表、栈、队列、递归行为、哈希表
 */
public class HelloContainer {

    /**
     * 单向链表->储存自己的值，以及下一节点的引用
     * 双向链表->储存自己的值，以及相邻节点的引用
     */
    @Test
    public static OneWayLinkedList linked(){
        OneWayLinkedList linkedList = new OneWayLinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        System.out.println(linkedList.toString());
        return linkedList;
    }


    /**
     * 翻转单向链表
     */
    @Test
    public void fzLinked(){
        OneWayLinkedList linkedList = new OneWayLinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        OneWayLinkedList.OneWayNode head = linkedList.getFirst();
        OneWayLinkedList.OneWayNode next = null;
        OneWayLinkedList.OneWayNode pre = null;
        do {
            next = head.getNext();
            head.setNext(pre);
            linkedList.setFirst(head);
            pre = head;
            head = next;
        }while (next!=null);
        System.out.println(linkedList.toString());
    }

    /**
     * 单向链表删除指定的位置的值
     */
    @Test
    public void delete(){
        OneWayLinkedList linkedList = linked();
        int delIndex = 2;
        OneWayLinkedList.OneWayNode delNode = linkedList.getFirst();
        OneWayLinkedList.OneWayNode preNode = null;
        OneWayLinkedList.OneWayNode nextNode = null;

        for(int i=0;i<linkedList.size;i++){
            if (i==0&&i==delIndex){
                linkedList.setFirst(delNode.getNext());
                delNode.setNext(null);
                delNode=null;
                break;
            }
            if(i==delIndex){
                    preNode.setNext(nextNode);
                    delNode.setNext(null);
                    delNode=null;
                    break;
            }
            preNode = delNode;
            delNode = delNode.getNext();
            nextNode = delNode.getNext();
        }
        System.out.println(linkedList.toString());

    }

}
