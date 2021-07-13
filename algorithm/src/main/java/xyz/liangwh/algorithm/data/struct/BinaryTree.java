package xyz.liangwh.algorithm.data.struct;


import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 二叉树
 */
public class BinaryTree {
    //根节点
    private Node root;

    public static int count = 0 ;
    public static Integer bc = new Integer(1000);
    public static  Node node = new Node();
    static {
        node.setValue(1000);
    }

    @SneakyThrows
    public static void main(String[] args) {
        //int count = 0;

        new Thread(()->{
            while (node.getValue()==1000){

            }
            System.out.println("over");
        }).start();
        Thread.sleep(100);
        node.setValue(10001);

    }

//    public static void main(String[] args) {
//        BinaryTree tree = new BinaryTree();
//        tree.add(8);
//        tree.add(1);
//        tree.add(2);
//        tree.add(3);
//        tree.add(7);
//        tree.add(11);
//        tree.add(4);
//        tree.add(1);
//        System.out.println(tree.find(7));
//       // tree.print();
//    }



    public void add(int value){
        if(root == null){
            root = new Node();
            root.setValue(value);
        }else{
            compAndSet(root,value);
        }
    }

    private void compAndSet(Node base,int tagVar){
        Node son = null;
        boolean left = false;
        //放左边
        if(base.comp(tagVar)>0){
            son = base.getLeft();
            left = true;
        }else{
            son = base.getRight();
        }
        if(son == null){
            son = new Node();
            son.setValue(tagVar);
            if(left){
                base.setLeft(son);
            }else{
                base.setRight(son);
            }
        }else{
            compAndSet(son,tagVar);
        }

    }

    public void del(int value){

    }

    public Node find(int value){
        if(root == null){
            return null;
        }else{
            return compAndFind(root,value);
        }
    }

    private Node compAndFind(Node base,int var){
        int x = base.comp(var);
        if(x == 0){
            return base;
        }else if(x > 0 ){
            base = base.getLeft();
        }else{
            base = base.getRight();
        }
        if(base!=null){
            base = compAndFind(base,var);
        }
        return base;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();


    }

}


/**
 * 链表节点
 */
@Data
class Node{

    private Node left;
    private Node right;
    private Integer value;

    public int comp(int tag){
        return value.compareTo(tag);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("var = ").append(this.value);
        sb.append(",left = ").append(this.left==null?null:this.left.getValue());
        sb.append(",right = ").append(this.right==null?null:this.right.getValue());

        sb.append("]");
        return sb.toString();
    }
}



