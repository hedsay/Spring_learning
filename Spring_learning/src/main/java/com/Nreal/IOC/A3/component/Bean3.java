package com.Nreal.IOC.A3.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class Bean3 {
    private static final Logger log = LoggerFactory.getLogger(Bean3.class);

    public Bean3() {
        log.debug("我被 Spring 管理啦");
    }
}
