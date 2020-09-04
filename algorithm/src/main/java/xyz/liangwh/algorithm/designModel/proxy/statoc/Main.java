package xyz.liangwh.algorithm.designModel.proxy.statoc;

public class Main {

    public static void main(String[] args) {
        TagetProxy proxy = new TagetProxy(new Taget());
        proxy.sout();
    }
}
