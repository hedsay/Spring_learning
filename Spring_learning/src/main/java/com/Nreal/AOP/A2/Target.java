package com.Nreal.AOP.A2;

public class Target implements Foo{
    @Override
    public void foo() {
        System.out.println("target foo");
    }

    @Override
    public int bar() {
        System.out.println("target bar");
        return 100;
    }
}
