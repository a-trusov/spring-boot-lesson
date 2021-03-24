package com.papont.lesson.config;


import com.papont.lesson.conditional.FirstConditional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Conditional(FirstConditional.class)

@Configuration
public class ApplicationConfiguration {

    public static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @PostConstruct
    public void init() {
        log.warn("app is loaded!!!");
    }
}
