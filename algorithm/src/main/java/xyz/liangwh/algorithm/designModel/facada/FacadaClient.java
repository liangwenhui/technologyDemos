package xyz.liangwh.algorithm.designModel.facada;

/**
 * 门面模式
 * 将多个功能统一入口SystemFacada，由Facada调用子模块/子功能
 *
 */
public class FacadaClient {

    public static void main(String[] args) {
        //模拟只依赖了 A B
        SystemFacada facada = new SystemFacada(new SystemA(),new SystemB(),null);

        facada.sout();
    }
}
