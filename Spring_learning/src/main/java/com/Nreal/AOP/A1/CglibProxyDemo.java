package com.Nreal.AOP.A1;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyDemo {
    static class Target {
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] param) {
        Target target = new Target();
        // 代理和目标是父子继承关系
        Target proxy = (Target) Enhancer.create(Target.class, new MethodInterceptor() {
            // 1.代理对象自己，2.代理类中执行的方法，3.方法参数，4.另一个方法对象
            @Override
            public Object intercept(Object p, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
//                Object res = method.invoke(target,args);// 用方法反射调用目标
//                //methodProxy 避免反射调用
//                Object res = methodProxy.invoke(target,args); // 内部没有用反射，需要目标(Spring使用);
                Object res = methodProxy.invokeSuper(p,args);// 内部没有用反射，需要代理;
                System.out.println("after...");
                return res;
            }
        });
        proxy.foo();
    }
}
