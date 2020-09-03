package xyz.liangwh.algorithm.designModel.decorator;

/**
 * 装饰者模式
 * 在不修改（目标类，对象）Man的属性，代码
 * 通过装饰模式，达到增加功能的目的。
 */
public class Main {
    public static void main(String[] args) {
        Man m = new HandDecorator(new Man());
        m = new HandDecorator(m);
        m = new LegDecorator(m);
        m = new LegDecorator(m);
        m.sayHello();
    }




}
