package com.pg.ehcache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class MyService {


    Logger log = LoggerFactory.getLogger(MyService.class);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    FactorialService factorialService;

    public String getCachedData(String input, boolean isCacheEnable) {

        if(isCacheEnable) {
            Cache cache = cacheManager.getCache("myCache");
            Cache.ValueWrapper valueWrapper = cache.get(input);

            if (valueWrapper == null || valueWrapper.get()==null) {
                cache.put(input, input);
                log.info("cache miss ... for input "+input);
                return input;
            } else {
                log.info("cache hit ... for input "+input);
                return (String) valueWrapper.get();
            }
        }

        log.info("inside getCachedData method ... for input "+input);

        return "Data for: " + input;
    }

    public long factorial(int num){
        log.info("inside factorial method calculating factorial for num "+num);
        return factorialService.calculatefactorial(num);
    }
}
