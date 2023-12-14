package com.Nreal.AOP.A1;

import java.lang.reflect.Proxy;

public class JdkProxyDemo {
    interface Foo{
        void foo();
    }
    //目标类与代理是兄弟关系
    static class Target implements Foo{
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] param) {
        //目标对象
        Target target = new Target();
        //代理对象 (1.目标对象的类加载器，2.接口，3.InvocationHandler)
        Foo proxy = (Foo) Proxy.newProxyInstance(Target.class.getClassLoader(),
                new Class[]{Foo.class},
                //1.代理对象自己，2.正在执行的方法，3.方法传过来的实际参数
                (p,method,args)->{
                    System.out.println("Proxy before...");
                    // 一般调用方法：目标.方法(参数);
                    // 使用反射：方法.invoke(目标，参数);
                    Object res = method.invoke(target,args);
                    System.out.println("Proxy after...");
                    return res;
                });
        //调用代理
        proxy.foo();
    }
}
