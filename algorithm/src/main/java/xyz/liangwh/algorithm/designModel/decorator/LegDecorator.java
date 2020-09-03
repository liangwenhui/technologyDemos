package xyz.liangwh.algorithm.designModel.decorator;

public class LegDecorator extends AbstractManDecorator{

    public LegDecorator(Man iman) {
        super(iman);
    }

    @Override
    public void sayHello() {
        iman.sayHello();
        addLeg();
    }

    private void addLeg(){
        System.out.println("我有一条腿");
    }
}
