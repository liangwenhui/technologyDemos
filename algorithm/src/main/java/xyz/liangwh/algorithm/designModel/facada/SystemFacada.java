package xyz.liangwh.algorithm.designModel.facada;

public class SystemFacada {

    private SystemA a;
    private SystemB b;
    private SystemC c;

    public SystemFacada(SystemA a, SystemB b, SystemC c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void sout(){
        if(a!=null){
            a.sout();
        }
        if(c!=null){
            c.sout();
        }
        if(b!=null){
            b.sout();
        }
    }
}
