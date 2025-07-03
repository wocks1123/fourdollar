package com.example.fourdollarbatch;

import com.example.fourdollardomain.FourdollarDomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {FourdollarBatchApplication.class, FourdollarDomainConfiguration.class})
public class FourdollarBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(FourdollarBatchApplication.class, args);
    }

}
