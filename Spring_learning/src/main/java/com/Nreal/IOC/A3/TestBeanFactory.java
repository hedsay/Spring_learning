package com.Nreal.IOC.A3;


import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

public class TestBeanFactory {
    private static final Logger log = LoggerFactory.getLogger(TestBeanFactory.class);

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
//        context.registerBean(ConfigurationClassPostProcessor.class); // 这个Bean 用于解析 @ComponentScan @Bean @Import @ImportResource
//        context.registerBean(MapperScannerConfigurer.class, bd -> { // 这个Bean 用于解析 @MapperScanner  Mybatis用
//            bd.getPropertyValues().add("basePackage", "com.Nreal.IOC.A3.mapper");
//        });

        context.registerBean(ComponentScanPostProcessor.class); // 解析 @ComponentScan
        context.registerBean(AtBeanPostProcessor.class); // 解析 @Bean

        context.refresh();
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
