package com.song.record.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Guava Cache创建方式一：CacheLoader
 *
 * refresh机制：
 *  - LoadingCache.refresh(K)在生成新的value的时候，旧的value依然会被使用
 *  - CacheLoader.reload(K,V)生成新的value过程中允许使用旧的value
 *  - CacheBuilder.refreshAfterWrite(long,TimeUnit)自动刷新cache
 */
public class LoadingCacheDemo {

    public static void main(String[] args){
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)//最大缓存数目
                .expireAfterAccess(1, TimeUnit.SECONDS)//缓存1秒后过期
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key;
                    }
                });
        cache.put("j", "java");
        cache.put("lion", "lionkk");
        try {
            System.out.println("第一次:"+cache.get("j"));
            TimeUnit.SECONDS.sleep(2);
            System.out.println("第二次:"+cache.get("j"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
