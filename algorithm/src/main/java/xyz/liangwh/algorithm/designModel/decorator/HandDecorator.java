package xyz.liangwh.algorithm.designModel.decorator;

public class HandDecorator extends AbstractManDecorator {
    public HandDecorator(Man iman) {
        super(iman);
    }

    @Override
    public void sayHello() {
        iman.sayHello();
        addHand();
    }

    private void addHand(){
        System.out.println("我有一只手");
    }

}
