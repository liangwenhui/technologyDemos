package xyz.liangwh.algorithm.designModel.proxy.statoc;

public class TagetProxy {

    private Taget taget;

    public TagetProxy (Taget taget){
        this.taget =taget;
    }

    public void sout(){
        if(taget==null){
            taget = new Taget();
        }

        System.out.println("befor");
        taget.sout();
        System.out.println("after");

    }

}
