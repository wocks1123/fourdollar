package com.example.fourdollardomain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {
        "com.example.fourdollardomain.*.infra.persistence"
})
@EntityScan(basePackages = {
        "com.example.fourdollardomain.*.domain"
})
@EnableJpaAuditing
public class FourdollarDomainConfiguration {
}
