package com.pg.ehcache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FactorialService {

    Logger log = LoggerFactory.getLogger(FactorialService.class);

    /*
     * below method have @Cacheable annotation on top of it
     * this annotation is for caching
     * but this only maintains caching of input for this method call not method's internal recursive call.
     * e.g. if we call calculatefactorial(5)->5*calculatefactorial(4)->4*calculatefactorial(3)->3*calculatefactorial(2)->2*calculatefactorial(1)
     * but in cache it only stores result for input 5 not for 4,3,2,1
     * now if you call calculatefactorial(5) again its result will return from cache
     * if you call calculatefactorial(4) again it calculate ->4*calculatefactorial(3)->3*calculatefactorial(2)->2*calculatefactorial(1)
     * but it store result of 4 in cache not for 3,2,1
     * this is out of box behaviour of this annotation.
     * */
    @Cacheable(value = "myCache", key="#num")
    public long calculatefactorial(int num){
        log.info("inside calculatefactorial method calculating factorial for num "+num);
        if(num<=0 || num==1){
            return 1L;
        }
        int nexNum = num-1;
        return num * calculatefactorial(nexNum);
    }
}
