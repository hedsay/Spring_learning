package com.Nreal.IOC.A1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Component2 {
    private static final Logger log = LoggerFactory.getLogger(Component2.class);

    @EventListener
    public void test(UserRegisteredEvent event){
        log.debug("{}",event);
        log.debug("发送短信");
    }
}
