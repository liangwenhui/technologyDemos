package xyz.liangwh.algorithm.container;

import lombok.Data;
import lombok.SneakyThrows;

@Data
public class OneWayLinkedList {
    OneWayNode first;
    OneWayNode laster;
    int size = 0;
    public void add(int i){
        OneWayNode next = new OneWayNode();
        next.setNext(null);
        next.setValue(i);
        if(first==null){
            first = next;
            laster = next;
        }else{
            laster.setNext(next);
            laster = next;
        }
        size++;
    }

    @Override
    @SneakyThrows
    public String toString(){
        StringBuffer sb = new StringBuffer();
        OneWayNode thisNode = first;
        sb.append("[");
        do{
         sb.append(thisNode.getValue());
            thisNode=thisNode.getNext();
        }while (thisNode!=null&&sb.append(",")!=null);
        sb.append("]");
        return sb.toString();
    }



    @Data
    class OneWayNode{

        int value;
        OneWayNode next;


    }
}
