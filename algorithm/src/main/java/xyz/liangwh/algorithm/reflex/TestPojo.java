package xyz.liangwh.algorithm.reflex;

import lombok.Data;

import java.io.Serializable;

@Data
@MyAnno(name="ym")
public class TestPojo extends SupTestPojo {

    public static final String  ARR = "1234123123";

    static {
        System.out.println("son init");
    }

    public String pubArr;

    private String priArr1;
    private String priArr2;

    public TestPojo(){

    }
    public TestPojo (String priArr1){
        this.priArr1 = priArr1;
    }

    @MyAnno(name ="mm1")
    public String m1(){
        return "ok";
    }

    private void m2(){
        System.out.println(1);
    }


}
