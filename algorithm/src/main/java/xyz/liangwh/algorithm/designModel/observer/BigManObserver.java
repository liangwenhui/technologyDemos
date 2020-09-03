package xyz.liangwh.algorithm.designModel.observer;

public class BigManObserver implements Observer{
    @Override
    public void doSomething(ThiefEvent event) {
        System.out.println("大个子给了小偷一拳");
    }
}
