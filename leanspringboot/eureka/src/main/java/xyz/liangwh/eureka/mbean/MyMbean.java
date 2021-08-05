package xyz.liangwh.eureka.mbean;

import javax.management.DynamicMBean;
import javax.management.StandardMBean;

public interface MyMbean   {
    String getName();

    String getPassword();

    String getPhone();

    void say();
}
