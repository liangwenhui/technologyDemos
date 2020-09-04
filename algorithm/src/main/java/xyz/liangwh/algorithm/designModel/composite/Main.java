package xyz.liangwh.algorithm.designModel.composite;

import lombok.Data;
import lombok.ToString;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * 想表示树状结构的时候使用（目录）
 */
public class Main {


    public static void main(String[] args) {
        TreeNode r =   new TreeNode("/");

        TreeNode home =   new TreeNode("home");
        TreeNode user =   new TreeNode("lwh");

        LeftNode f1 = new LeftNode("eureka.jar");
        LeftNode f2 = new LeftNode("application.yml");
        LeftNode f3 = new LeftNode("info.log");
        r.add(home);
        home.add(user);
        user.add(f1);
        user.add(f2);
        user.add(f3);

        System.out.println(r);
    }




}


interface Node{
    void sout();
}
@Data
@ToString
class LeftNode implements Node{
    private String name;

    public LeftNode(String name) {
        this.name = name;
    }

    @Override
    public void sout() {
        System.out.println(name);
    }

}
@Data
@ToString
class TreeNode implements Node{
    private String name;
    private List<Node> nodes = new ArrayList<>();

    public TreeNode(String name) {
        this.name = name;
    }
    @Override
    public void sout() {
        System.out.println(name);
    }

    public void add(Node node){
        nodes.add(node);
    }


}
