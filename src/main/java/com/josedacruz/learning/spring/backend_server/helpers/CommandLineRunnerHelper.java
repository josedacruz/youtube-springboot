package com.josedacruz.learning.spring.backend_server.helpers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerHelper implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunnerHelper.run");
        System.out.println("args: " + args[0]);
    }
}
