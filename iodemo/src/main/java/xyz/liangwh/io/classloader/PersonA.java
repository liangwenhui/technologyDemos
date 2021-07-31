package xyz.liangwh.io.classloader;

public class PersonA {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader classLoader = new MyClassLoader();
        Class c = Class.forName("Person",true,classLoader);
        Object o = c.newInstance();
        System.out.println(o.toString());
        System.out.println(o.getClass().getClassLoader().toString());

    }

    static {
        System.out.println("this is A");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
