package xyz.liangwh.algorithm.reflex;

import java.io.Serializable;
@MyAnno2(name = "123")
public class SupTestPojo implements Serializable {

    static {
        System.out.println("sup init");
    }

    private String supPriArr;

    public String supPubArr;

    public void supPubM1(){};

    private void supPriM2(){};
}
