package com.song.record.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Guava Cache创建方式二：callable callback
 */
public class CallbackDemo {

    public static void main(String[] args){
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();
        try{
            String result = cache.get("java", () -> "hello java");
            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
