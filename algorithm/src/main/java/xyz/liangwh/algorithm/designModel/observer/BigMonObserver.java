package xyz.liangwh.algorithm.designModel.observer;

public class BigMonObserver implements  Observer{

    @Override
    public void doSomething(ThiefEvent event) {
        System.out.println("大妈看到小偷，大喊大叫，抓贼！");
    }
}
