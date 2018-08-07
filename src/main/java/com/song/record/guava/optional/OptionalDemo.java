package com.song.record.guava.optional;

import com.google.common.base.Optional;

/**
 * Optional是用来处理null的。一个Optional实例可能包含非null的引用（称之为引用存在），也可能什么也不包括（称之为引用缺失），
 *
 */
public class OptionalDemo {

    public static void main(String[] args){
        Integer value1 = null;
        Integer value2 = 10;
        /*
         * 创建指定引用的Optional实例，若引用为null则快速失败返回absent()
         * absent()创建引用缺失的Optional实例
         */
        Optional<Integer> a = Optional.fromNullable(value1);
        //返回包含给定的非空引用Optional实例
        Optional<Integer> b = Optional.of(value2);
        System.out.println(sum(a, b));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b){
        //isPresent():如果Optional包含非null的引用（引用存在），返回true
        System.out.println("first param is present: "+a.isPresent());
        System.out.println("second param is present: "+b.isPresent());
        //返回Optional所包含的引用，若引用缺失，返回指定的值
        Integer value1 = a.or(1);
        //返回所包含的实例，它必须存在，通常在调用该方法时会调用isPresent()判断是否为null
        Integer value2 = b.get();
        return value1 + value2;
    }

}
