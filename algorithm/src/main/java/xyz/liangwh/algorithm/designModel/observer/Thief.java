package xyz.liangwh.algorithm.designModel.observer;

import java.util.ArrayList;

public class Thief {

    private ArrayList<Observer> observers = new ArrayList<>() ;

    public void  addObserver(Observer... observers){
        for(Observer observer : observers){
            this.observers.add(observer);
        }
    }

    //小偷有没有动手
    private volatile boolean dongshou = false;

    public void  setDs(boolean s){
         dongshou = s;
    }

    public boolean isDs(){
        return dongshou;
    }

    public void dongshou() throws InterruptedException {
        while (!dongshou){
            System.out.println("伺机行动...");
            Thread.sleep(1000);
        }
        System.out.println("小偷  偷东西了！！！！");
        ThiefEvent event = new ThiefEvent();
        event.setSource(this);
        zaoyang(event);

    }

   private void zaoyang(ThiefEvent event){
       for(Observer observer : observers){
           observer.doSomething(event);
       }
   }
}
