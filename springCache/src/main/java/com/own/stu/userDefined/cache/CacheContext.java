package com.own.stu.userDefined.cache;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by bf50 on 2016/2/19.
 */
@Service
public class CacheContext<T> {

    private Map<String, T > cache = Maps.newConcurrentMap();

    public T get(String key){
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value){
        cache.put(key, value);
    }

    // 根据 key 来删除缓存中的一条记录
    public void evictCache(String key) {
        if(cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    // 清空缓存中的所有记录
    public void evictCache() {
        cache.clear();
    }

}
