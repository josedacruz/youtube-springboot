package com.josedacruz.learning.spring.backend_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyClass {
    private static final Logger logger = LoggerFactory.getLogger(LegacyClass.class);
    public LegacyClass() {
        logger.info("@Bean LegacyClass created");
    }

    public void initialization() {
        logger.info("@Bean LegacyClass initialization");
    }

    public void destroy() {
        logger.info("@Bean LegacyClass destroyed");
    }

    public void doSomething() {
        logger.info("LegacyClass doing something");
    }

}
