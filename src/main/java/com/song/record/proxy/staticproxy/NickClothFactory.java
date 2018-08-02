package com.song.record.proxy.staticproxy;

/**
 * 被代理类 （目标对象）
 */
public class NickClothFactory implements ClothFactory {

    public void productCloth() {
        System.out.println("Nick工厂生产衣服");
    }
}
