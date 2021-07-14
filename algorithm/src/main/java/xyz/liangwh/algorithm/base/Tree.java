package xyz.liangwh.algorithm.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {
    public Node root;
    static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }
    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输
     * 入前序遍历序列 {1,2,4,7,3,5,6,8} 和中序遍历序列 {4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * D(root) L(left node) R(right node)
     *
     * DLR--前序遍历（根在前，从左往右，一棵树的根永远在左子树前面，左子树又永远在右子树前面 ）
     *
     * LDR--中序遍历（根在中，从左往右，一棵树的左子树永远在根前面，根永远在右子树前面）
     *
     * LRD--后序遍历（根在后，从左往右，一棵树的左子树永远在右子树前面，右子树永远在根前面）
     */
    @Test
    public void rBinaryTree(){
        List<Integer> DLR = Arrays.asList(1,9,6,4,7,2,3,8,11,5,10,21,23);
        List<Integer> LDR = Arrays.asList(4,6,2,7,3,9,8,1,5,11,10,23,21);
        findInLdr(LDR,DLR,0,null);
        System.out.println(tree);
    }
    public static Tree tree = new Tree();

    private static int findInLdr(List<Integer> LDR,List<Integer> DLR,int dlr_root_index,Node parent){
        int root = DLR.get(dlr_root_index++);
        int tmp_root_index = LDR.indexOf(root);
        boolean hasL = true;
        boolean hasR = true;
        List<Integer> leftLdr = null;
        List<Integer> rightLdr = null;


        if(tree.root==null){
            Node current = new Node(root);
            tree.root = current;
            parent = current;
        }
        //无左节点
        if(tmp_root_index==0){
            hasL = false;
        }
        //无右节点
        if(tmp_root_index==(LDR.size()-1)){
            hasR = false;
        }
        if(hasL){
            leftLdr = LDR.subList(0,tmp_root_index);
            int left = DLR.get(dlr_root_index);
            Node ln = new Node(left);
            parent.left = ln;
            dlr_root_index = findInLdr(leftLdr,DLR,dlr_root_index,ln);
        }
        if(hasR){
            rightLdr = LDR.subList(tmp_root_index+1,LDR.size());
            int right = DLR.get(dlr_root_index);
            Node lr = new Node(right);
            parent.right = lr;
            dlr_root_index = findInLdr(rightLdr,DLR,dlr_root_index,lr);
        }


        return dlr_root_index;
    }






}
