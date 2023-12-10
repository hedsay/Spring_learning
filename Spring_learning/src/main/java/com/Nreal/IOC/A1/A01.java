package com.Nreal.IOC.A1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class A01 {
    private static final Logger log = LoggerFactory.getLogger(A01.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A01.class,args);
        context.getBean(Component1.class).register();
    }
}
