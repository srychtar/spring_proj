package com.example.proj_zal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.example.proj_zal.repository"})
@EntityScan(basePackages = {"com.example.proj_zal.entity"})
public class ProjZalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjZalApplication.class, args);
    }

}
