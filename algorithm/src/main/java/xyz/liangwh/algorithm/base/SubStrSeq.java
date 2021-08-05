package xyz.liangwh.algorithm.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的所有子序
 */
public class SubStrSeq {


    public static void main(String[] args) {
        String str = "abcde";
        char[] chars = str.toCharArray();
        List<String> res = new ArrayList();
        choose(chars,res,0,"");
        res.stream().forEach((s)->{
            System.out.println(s);
        });


    }
    //每个字符都可以选择要或者不要 深度优先遍历
    public static void choose(char[] chars,List<String> res,int index,String var){
        if(index == chars.length){
            res.add(var);
            return;
        }
        choose(chars,res,index+1,var);
        choose(chars,res,index+1,var+chars[index]);
    }


}
