package com.example.fourdollarapi;

import com.example.fourdollardomain.FourdollarDomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {FourdollarApiApplication.class, FourdollarDomainConfiguration.class})
public class FourdollarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FourdollarApiApplication.class, args);
    }

}
