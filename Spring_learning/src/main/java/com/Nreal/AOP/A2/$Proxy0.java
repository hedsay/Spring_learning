package com.Nreal.AOP.A2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public class $Proxy0 extends Proxy implements Foo{

//    private A2.InvocationHandler h;
//
//    public $Proxy0(A2.InvocationHandler h){
//        this.h = h;
//    }

    protected $Proxy0(InvocationHandler h) {
        super(h);
    }

    static Method foo;//避免调用一次就创建一个方法对象
    static Method bar;
    static {
        try {
            foo = Foo.class.getMethod("foo");
            bar = Foo.class.getMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    @Override
    public void foo() {
        try {
            h.invoke(this,foo,new Object[0]);//代理类为自己this
        } catch (RuntimeException | Error e) {//运行时异常 直接抛
            throw e;
        } catch (Throwable e) {//检查异常 转换未运行异常
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int bar() {
        try {
            Object result = h.invoke(this, bar, new Object[0]);
            return (int) result;
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
