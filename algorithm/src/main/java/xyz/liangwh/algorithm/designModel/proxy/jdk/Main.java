package xyz.liangwh.algorithm.designModel.proxy.jdk;

import xyz.liangwh.algorithm.designModel.proxy.statoc.Taget;

public class Main {


    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        TagetJdkProxy proxy = new TagetJdkProxy();
        proxy.setO(new JTaget());
        ITaget taget = (ITaget)proxy.createJTagetProxyObject();
        taget.sout();
        proxy.setO(new Taget());
        taget = (ITaget)proxy.createJTagetProxyObject();
        taget.sout();

    }
}
