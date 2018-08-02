package com.song.record.proxy.cglib;

/**
 * 创建业务类和代理类对象，然后通过代理类对象的getInstance(业务类对象)返回一个动态代理类对象(它是业务类的子类，
 * 可以用业务类引用指向它.最后通过动态代理类对象进行方法调用)
 */
public class Test {

    public static void main(String[] args){
        BookImpl bookImpl = new BookImpl();
        BookCGlib cglib = new BookCGlib();
        BookImpl bookCGlib = (BookImpl) cglib.getInstance(bookImpl);
        bookCGlib.addBook();
    }

}
