package com.pg.ehcache;

import com.pg.ehcache.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MainApplication implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(MainApplication.class);

    @Autowired
    MyService service;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.getCachedData("prateek",true);
        service.getCachedData("prateek",false);
        service.getCachedData("goel",true);
        service.getCachedData("goel",true);
        log.info("Result "+service.factorial(0));
        log.info("Result "+service.factorial(1));
        log.info("Result "+service.factorial(2));
        log.info("Result "+service.factorial(3));
        log.info("Result "+service.factorial(4));
        log.info("Result "+service.factorial(5));
        log.info("Result "+service.factorial(5));
        log.info("Result "+service.factorial(10));
    }
}
