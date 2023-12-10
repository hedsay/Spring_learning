package com.Nreal.IOC.A2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean 的定义（class, scope, 初始化, 销毁）
        AbstractBeanDefinition beanDefinition =
                BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);
        /**
         * BeanFactory 后处理器
         */
        // 如何获取@Configuration这个Bean下的所有Bean?
        // 给 BeanFactory 添加一些常用的后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // BeanFactory 后处理器主要功能，补充了一些 bean 定义
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });
        /**
         * Bean 后处理器
         */
        // 如何找到依赖注入的Bean?
        // Bean 后处理器, 针对 bean 的生命周期的各个阶段提供扩展, 例如 @Autowired @Resource ...
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanPostProcessor->{
            System.out.println(">>>>>>>>"+beanPostProcessor);
            // 添加BeanFactory与 Bean后处理器 之间的联系
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        });
        for(String name:beanFactory.getBeanDefinitionNames()){
            System.out.println(name);
        }

        // 所有的bean先创建BeanDefinition，真正调用才创建实例，单例对象建议一开始就创建好
        beanFactory.preInstantiateSingletons(); // 准备好所有单例
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
        System.out.println(beanFactory.getBean(Bean1.class).getBean2());
    }


    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }
        @Bean
        public Bean2 bean2(){
            return new Bean2();
        }
    }
    static class Bean1{
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);
        public Bean1() {
            log.debug("构造 Bean1()");
        }
        @Autowired
        private Bean2 bean2;
        public Bean2 getBean2() {
            return bean2;
        }
    }
    static class Bean2{
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);
        public Bean2() {
            log.debug("构造 Bean2()");
        }
    }
}
