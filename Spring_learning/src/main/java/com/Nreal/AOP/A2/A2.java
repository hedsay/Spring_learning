package com.Nreal.AOP.A2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class A2 {

//    interface InvocationHandler {
//        Object invoke(Object proxy,Method method,Object[] args) throws Throwable;
//    }

    public static void main(String[] param) {
        Foo proxy = new $Proxy0(new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
                // 1.动态增强
                System.out.println("before...");
                // 2.反射调用目标方法
                return method.invoke(new Target(),args);
            }
        });
        proxy.foo();
        proxy.bar();
    }
}
