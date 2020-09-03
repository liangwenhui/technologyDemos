package xyz.liangwh.algorithm.designModel.decorator;

public abstract class AbstractManDecorator extends Man {

    protected Man iman;

    public AbstractManDecorator(Man iman){
        this.iman=iman;
    }


    @Override
    public void sayHello() {
        iman.sayHello();
    }
}
