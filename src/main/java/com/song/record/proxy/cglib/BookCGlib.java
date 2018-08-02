package com.song.record.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 实现MethodInterceptor方法代理接口，创建代理类
 *
 * CGlib是针对类来实现代理的，原理是对指定的业务类生成一个子类，覆盖其中业务方法实现代理
 * 因为采用的是继承，所以不能对final修饰的类进行代理
 */
public class BookCGlib implements MethodInterceptor {

    //业务类对象，供代理方法中进行真正的业务方法调用
    private Object target;

    public Object getInstance(Object target){
        //给业务方法赋值
        this.target = target;
        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //为加强器指定要代理的业务类（即:为下面生成的代理类指定父类）
        enhancer.setSuperclass(this.target.getClass());
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而CallBack则需要实现intercept()方法进行拦截
        enhancer.setCallback(this);
        return enhancer.create();
    }

    //实现回调方法
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("---------预处理--------");
        proxy.invokeSuper(obj, args);
        System.out.println("--------调用后操作------");
        return null;
    }
}
