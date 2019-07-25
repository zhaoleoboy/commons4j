package com.ying;

import com.ying.filter.PermisFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ProviderApplication {

    @Bean
    PermisFilter permisFilter() {
        return new PermisFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
