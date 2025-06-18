package com.josedacruz.learning.spring.backend_server.configurations;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class MyBeanNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if(definition.getBeanClassName() != null && definition.getBeanClassName().contains(".repositories.")) {
            return "rep_" + definition.getBeanClassName();
        }

        if(definition.getBeanClassName() != null && definition.getBeanClassName().contains(".services.")) {
            return "serv_" + definition.getBeanClassName();
        }

        if(definition.getBeanClassName() != null && definition.getBeanClassName().contains(".rest.")) {
            return "rest_" + definition.getBeanClassName();
        }

        return definition.getBeanClassName();
    }
}
