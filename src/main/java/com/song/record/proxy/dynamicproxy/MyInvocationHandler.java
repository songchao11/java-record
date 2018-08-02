package com.song.record.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 调用InvocationHandler实现接口，创建动态代理对象
 */
public class MyInvocationHandler implements InvocationHandler {

    //实现了接口的被代理类对象的声明（目标对象）
    Object obj;

    /*
    *  ①给被代理类对象实例化
    *  ②返回一个代理类的对象
     */
    public Object blind(Object obj){
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    /*
    * 当通过代理类的对象发起对被重写的方法的调用时，都会转化为对如下invoke方法的调用
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method方法的返回值
        Object returnVal = method.invoke(proxy, args);
        return returnVal;
    }
}
