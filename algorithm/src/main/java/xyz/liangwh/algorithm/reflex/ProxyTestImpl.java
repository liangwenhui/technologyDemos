package xyz.liangwh.algorithm.reflex;

public class ProxyTestImpl implements ProxyTest{
    @Override
    public void say(String msg) {
        System.out.println("im impl say:"+msg);
    }

    @Override
    public String get(String msg) {
        return msg;
    }
}
