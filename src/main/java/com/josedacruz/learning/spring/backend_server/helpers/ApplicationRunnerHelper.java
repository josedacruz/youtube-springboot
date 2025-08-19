package com.josedacruz.learning.spring.backend_server.helpers;

import com.josedacruz.learning.spring.backend_server.services.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerHelper implements ApplicationRunner {

    @Autowired
    private EntitiesService entitiesService;

    // needs the parameter '--display=entities'
    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("-".repeat(100));
        if( args.containsOption("display") && args.getOptionValues("display").contains("entities")) {
            System.out.println("ApplicationRunnerHelper.run.display=entities");
            entitiesService.getEntities().forEach(System.out::println);
        }

    }
}
