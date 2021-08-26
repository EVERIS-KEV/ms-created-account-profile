package com.everis.createdaccountprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@EnableEurekaClient
@SpringBootApplication
@Slf4j
public class CreatedAccountProfileApplication { 
  public static void main(String[] args) {
    SpringApplication.run(CreatedAccountProfileApplication.class, args);
    log.info("Servicio cuentas por perfil.");

  }
}
