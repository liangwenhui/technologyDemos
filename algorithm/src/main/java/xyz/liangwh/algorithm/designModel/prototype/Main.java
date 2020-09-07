package xyz.liangwh.algorithm.designModel.prototype;

import lombok.Data;

public class
Main {

    public static void main(String[] args) throws CloneNotSupportedException {
            Stu s1 = new Stu();
            s1.setCode("111");
            s1.setName("wg");
        System.out.println(s1);
        Stu s2 = (Stu)s1.clone();
        System.out.println(s2);
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        String formatSql = "SELECT %s.nextval as nextval";
        String sql = String.format(formatSql, new Object[] { "asd_sad".toUpperCase() });
        System.out.println(sql);
        Long.parseLong("null");
    }
}

@Data

class Stu implements Cloneable{
    private String name;
    private String code;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Stu)super.clone();
    }
}
