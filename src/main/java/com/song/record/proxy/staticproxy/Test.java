package com.song.record.proxy.staticproxy;

/**
 * Created by song on 2018/8/2.
 */
public class Test {

    public static void main(String[] args){
        NickClothFactory nick = new NickClothFactory();
        ProxyFactory proxy = new ProxyFactory(nick);
        proxy.productCloth();
    }

}
