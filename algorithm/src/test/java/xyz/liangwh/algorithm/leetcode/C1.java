package xyz.liangwh.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class C1 {

    @Test
    public void CheckPermutation() {
        boolean falg = false;
        String s1,  s2;
        s1 = "abc";
        s2 = "cab";
        LinkedList list = new LinkedList();

        if(s1.length()!=s2.length()){

        }else{
            String[] chars1 = s1.split("");//toCharArray();
            String[] chars2 = s2.split("");//toCharArray();

            for(int i=0;i<s1.length();i++){
                list.add(chars1[i]);
                list.remove(chars2[i]);

            }
            falg = list.size()==0?true:false;
        }
        System.out.println(falg);

    }

}
