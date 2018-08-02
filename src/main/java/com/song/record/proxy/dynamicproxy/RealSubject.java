package com.song.record.proxy.dynamicproxy;

/**
 * 被代理类
 */
public class RealSubject implements Subject {
    public void action() {
        System.out.println("我是被代理类");
    }
}
