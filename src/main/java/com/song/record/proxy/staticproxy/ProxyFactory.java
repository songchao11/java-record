package com.song.record.proxy.staticproxy;

/**
 * 代理类（代理对象）
 */
public class ProxyFactory implements ClothFactory {

    //接收保存目标对象
    ClothFactory clothFactory;

    //创建代理类的对象时，实际传入一个被代理类的对象
    public ProxyFactory(ClothFactory cFactory){
        this.clothFactory = cFactory;
    }

    public void productCloth() {
        System.out.println("代理类开始执行，收代理费");
        clothFactory.productCloth();
    }
}
