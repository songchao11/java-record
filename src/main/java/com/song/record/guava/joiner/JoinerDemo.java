package com.song.record.guava.joiner;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Joiner来处理字符串加入操作，对象等
 * Joiner实例不可变的，因此是线程安全的
 */
public class JoinerDemo {

    public static void main(String[] args){
        /*
         * on:制定拼接符号
         * skipNulls:忽略null，返回一个新的Joiner实例
         * useForNull("hello"):null的地方都用hello来代替
         */
        StringBuilder sb = new StringBuilder();
        Joiner.on(",").skipNulls().appendTo(sb, "hello", "guava");
        System.out.println(sb);
        System.out.println(Joiner.on(",").useForNull("none").join(1, null, 3));
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,3,2,5,3,null,8,4)));
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
    }

}
