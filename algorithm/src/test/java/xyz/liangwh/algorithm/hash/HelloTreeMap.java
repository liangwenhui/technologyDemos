package xyz.liangwh.algorithm.hash;

import java.util.TreeMap;

/**
 * 有序表 有hashmap的API,但是会给我们用key排序
 */
public class HelloTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(2,"我是1");
        treeMap.put(9,"我是1");
        treeMap.put(4,"我是1");
        treeMap.put(1,"我是1");
        treeMap.put(8,"我是1");
        treeMap.put(3,"我是1");
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.floorKey(5));
        System.out.println(treeMap.ceilingKey(5));
        System.out.println(treeMap.higherKey(3));



    }

}
