package xyz.liangwh.algorithm.hash;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Objects;

public class HelloMap {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        Integer a = 128;
        Integer b = 129;
        Integer c = 128;
        /**
         * integer -128 ~ 127 shot范围，有缓存
         */
        System.out.println(a==c);
        map.put(a,"i am a");
        map.put(c,"i am c");
        System.out.println(map.size());

        T t1 = new T(1);
        T t2 = new T(1);
        System.out.println(t1==t2);
        HashMap<T, String> tmap = new HashMap<>();
        tmap.put(t1,"i am c");
        tmap.put(t2,"i am c");
        System.out.println(tmap.size());
    }



    static class T{
        public int value;

        T(int value){
            this.value=value;
        }
        /**
         * hashmap 如果key是java定义的数据类型，那么map的key是[值传递]不管你是不是不同的引用对象。
         * 如果 key是普通创建的对象，那么map的key则是引用传递，两个不同的引用对象，会创建2个key。
         * 但是如果重写了equals和hashcode方法，是可以让2个不同引用，但值相同的普通对象，像java定义的数据类型一样处理
         */
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            T t = (T) o;
//            return value == t.value;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(value);
//        }
    }


}
