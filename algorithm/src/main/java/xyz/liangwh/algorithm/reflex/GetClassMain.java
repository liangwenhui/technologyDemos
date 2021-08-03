package xyz.liangwh.algorithm.reflex;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

@Slf4j
public class GetClassMain {

    /**
     * 获取class对象，通过
     *  1.通过类.class
     *  2.通过 Class.forName("")
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {


        Class clazz = TestPojo.class;
        //注意使用全名，且如果没有initialize，是不会执行static代码块
        Class supClazz = Class.forName("xyz.liangwh.algorithm.reflex.SupTestPojo",true,clazz.getClassLoader());

        System.out.println(clazz);
        System.out.println(supClazz);

        //获取类名
        log.info("获取全名 {}",clazz.getName());
        log.info("获取类名 {}",clazz.getSimpleName());

        //获取修饰符
        log.info("获取修饰符 {}",clazz.getModifiers());
        log.info("判断是否public {}",String.valueOf(Modifier.isPublic(clazz.getModifiers())));

        log.info("获取包信息 {}",clazz.getPackage());
        //获取父类
        Class pclazz = clazz.getSuperclass();
        log.info("sup class name : {}" , pclazz.getName());
        //获取接口 无法获取父类实现的接口
        Class[] interfaces = clazz.getInterfaces();
        log.info("接口 {}",interfaces);

        //获取构造器
        Constructor[] constructors = clazz.getConstructors();
        log.info("构造器：{}",constructors);
        Constructor constructor = clazz.getConstructor(String.class);
        log.info("指定参数构造器 {}",constructor);

        Class[] parameterTypes = constructor.getParameterTypes();
        log.info("获取构造器参数 {}",parameterTypes);
        
        //初始化对象 可以通过clazz对象初始化实例，也可以使用构造器
        TestPojo o1 = (TestPojo)clazz.newInstance();
        TestPojo o2= (TestPojo)constructor.newInstance("abc");
        log.info("o1 {}",o1.getPriArr1());
        log.info("o2 {}",o2.getPriArr1());

        log.info("\r\n-------------------------------");

        //获取方法
        //1.获取public方法,可以获取到继承的父类方法
        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            log.info("获取public方法 {}",m);
        }
        log.info("\r\n-------------------------------");

        //2.获取所有成员方法,无论修饰符，但是无法获取父类的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method m : declaredMethods){
            log.info("获取public方法 {}",m);
        }
        log.info("\r\n-------------------------------");


        //invoke调用
        Method getPriArr1 = clazz.getMethod("getPriArr1", null);
        log.info("调用方法 {}",getPriArr1.invoke(o2,null));


        //获取成员变量 获取所有public，包括继承的
        Field[] fields = clazz.getFields();
        log.info("\r\n-------------------------------");
        for (Field f : fields){
            log.info("field {}",f);
        }
        log.info("\r\n-------------------------------");

        //获取所有成员变量
        log.info("\r\n-------------------------------");
        Field[] declaredFields = clazz.getDeclaredFields();

        log.info("\r\n-------------------------------");
        //成员变量读写
        Field priAttr1 = clazz.getDeclaredField("priArr1");
        //私有变量需要设置可访问
        priAttr1.setAccessible(true);
        priAttr1.set(o1,"liangwh");
        log.info("o1 设置之后 {} {}",priAttr1.get(o1),o1.getPriArr1());
        //只能获取到RUNTIME的注解
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation a : annotations){
            log.info("anno: {} ", a);
        }

        Annotation annotation = clazz.getAnnotation(MyAnno.class);
        log.info("my anno {}",((MyAnno)annotation).name());

        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        for(Annotation a : declaredAnnotations){
            log.info("declaredAnnotations: {} ", a);
        }
    }


}
