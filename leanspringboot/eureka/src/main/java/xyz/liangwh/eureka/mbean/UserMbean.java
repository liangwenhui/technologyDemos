package xyz.liangwh.eureka.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(
        objectName = "lwh.jmx:type=UserMBean",
        description = "this is a test"
)
public class UserMbean  implements MyMbean {
    private String version = "12312313.1231";

    @ManagedAttribute
    @Override
    public String getName() {
        return "liangwh";
    }
    @ManagedAttribute
    @Override
    public String getPassword() {
        return "1366";
    }
    @ManagedAttribute
    @Override
    public String getPhone() {
        return "13660160132";
    }
    @ManagedOperation(description = "do say")
    @Override
    public void say() {
        System.out.println("do say ------------------------------------------------");
    }
}
