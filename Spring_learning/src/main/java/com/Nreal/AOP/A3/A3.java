package com.Nreal.AOP.A3;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class A3 {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        Target target = new Target();
        proxy.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                // 反射调用
//                return method.invoke(target, args);
                // 内部无反射, 结合目标用
                return methodProxy.invoke(target, args);
                // 内部无反射, 结合代理用
//                return methodProxy.invokeSuper(p, args);
            }
        });
        proxy.save();
        proxy.save(1);
        proxy.save(2L);
    }
}
