package xyz.liangwh.algorithm.designModel.chainOfResponsibility;

/**
 * 责任链模式
 * 按顺序处理，处理失败，或达不到条件的任务，让下一个处理者处理，直至成功为止。
 *
 * 应用实例： 1、红楼梦中的"击鼓传花"。 2、JS 中的事件冒泡。 3、JAVA WEB 中 Apache Tomcat 对 Encoding 的处理，Struts2 的拦截器，jsp servlet 的 Filter。
 *
 * 优点： 1、降低耦合度。它将请求的发送者和接收者解耦。 2、简化了对象。使得对象不需要知道链的结构。 3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。 4、增加新的请求处理类很方便。
 *
 * 缺点： 1、不能保证请求一定被接收。 2、系统性能将受到一定影响，而且在进行代码调试时不太方便，可能会造成循环调用。 3、可能不容易观察运行时的特征，有碍于除错。
 *
 * 使用场景： 1、有多个对象可以处理同一个请求，具体哪个对象处理该请求由运行时刻自动确定。 2、在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。 3、可动态指定一组对象处理请求。
 *
 */
public class Main {


    public static void main(String[] args) {
        HandleA a = new HandleA();
        a.handle("D");
        a.handle("C");

    }



}

interface  Handle{
    void handle(String name);
}

class HandleA implements Handle{
    private Handle next = new HandleB();
    public  void handle(String name){
        if(name.equals("A")){
            System.out.println("this is A");
        }else{
            if(next!=null){
                next.handle(name);
            }else {
                System.out.println("无人处理");
            }
        }
    }
}

class HandleB implements Handle{
    private Handle next = new HandleC();

    public  void handle(String name){
        if(name.equals("B")){
            System.out.println("this is B");
        }else{
            if(next!=null){
                next.handle(name);
            }else {
                System.out.println("无人处理");
            }
        }
    }
}


class HandleC implements Handle{
    private Handle next = null;
    public  void handle(String name){
        if(name.equals("C")){
            System.out.println("this is C");
        }else{
            if(next!=null){
                next.handle(name);
            }else {
                System.out.println("无人处理");
            }
        }
    }
}