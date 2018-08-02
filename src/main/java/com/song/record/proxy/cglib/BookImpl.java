package com.song.record.proxy.cglib;

/**
 * 首先定义业务类，无需实现接口（当然实现接口也是可以的，不影响）
 */
public class BookImpl {

    public void addBook(){
        System.out.println("新增书籍");
    }

}
