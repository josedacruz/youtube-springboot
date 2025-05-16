package com.josedacruz.learning.spring.backend_server.configurations;

import com.josedacruz.learning.spring.backend_server.LegacyClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LegacyBeansConfiguration {

    @Bean(name = "legacyClass", destroyMethod = "destroy", initMethod = "initialization")
    public LegacyClass legacyClass() {
        return new LegacyClass();
    }

}
