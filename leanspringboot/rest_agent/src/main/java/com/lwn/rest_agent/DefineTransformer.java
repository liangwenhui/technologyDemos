package com.lwn.rest_agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.net.URL;
import java.security.ProtectionDomain;

public class DefineTransformer implements ClassFileTransformer {

    private String targetClassName ;

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public DefineTransformer(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    /**
     * <> 需要 /*<>/*
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        String compClassName = className.replace("/", ".").replace("\\", ".");
        if(compClassName.equals(targetClassName)){
            try {

                final ClassPool aDefault = ClassPool.getDefault();
                final CtClass clazz = aDefault.get(targetClassName);
                final CtClass tmpClazz = aDefault.get(TempClazz.class.getName());
                System.out.println("1111111111111111111111111111111111111");
                CtMethod restTemplateCustomizer = tmpClazz.getDeclaredMethod("restTemplateCustomizer");
                CtClass[] nestedClasses = tmpClazz.getNestedClasses();
                CtClass[] nestedClasses2 = new CtClass[2];

                for(CtClass c : nestedClasses){
                    if(c.getSimpleName().endsWith("LwhRestTemplateCustomizer")){
                        nestedClasses2[1] = c;
                    }else{
                        nestedClasses2[0] = c;
                    }
                }
                for(CtClass c : nestedClasses2){
                    String name = c.getSimpleName();
                    CtClass c1 = clazz.makeNestedClass(name.split("\\$")[1], true);
                    for(CtClass i : c.getInterfaces()){
                        c1.addInterface(i);
                    }
                    for(CtField f : c.getDeclaredFields()){
                        CtField nf = new CtField(f.getType(), f.getName(), c1);
                        c1.addField(nf);
                    }
                    for(CtConstructor con:c.getConstructors()){
                        CtConstructor constructor = new CtConstructor(con.getParameterTypes(), c1);
                        constructor.setBody(con,null);
                        c1.addConstructor(constructor);
                    }
                    for(CtMethod m : c.getDeclaredMethods()){
                        CtMethod mm = new CtMethod(m.getReturnType(),m.getName(), m.getParameterTypes(), c1);
                        mm.setBody(m,null);
                        c1.addMethod(mm);
                    }
                    //c1.writeFile(loader.getResource("/").getPath());
                    Class<?> aClass = c1.toClass();
//                    System.out.println(aClass.getName());
//                    Object o = aClass.newInstance();
                    //Class.forName(aClass.getName());
                    //ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                    //systemClassLoader.loadClass(aClass.getName());

                    //c1.detach();
                }
                CtMethod method = clazz.getDeclaredMethod("restTemplateCustomizer");
//                method.setBody(restTemplateCustomizer,null);
                method.setBody("{System.out.println(\"OSS重写 LoadBalancerInterceptorConfig restTemplateCustomizer !!!\");" +
                        "org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer customizer = new org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration$LoadBalancerInterceptorConfig$LwhRestTemplateCustomizer($1) ;" +
                        "System.out.println(\"OSS重写 LoadBalancerInterceptorConfig restTemplateCustomizer end!!!\");return customizer;}");
                //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
                clazz.detach();
                byte[] byteCode = clazz.toBytecode();
                System.out.println("修改成功");
                return byteCode;

                

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
