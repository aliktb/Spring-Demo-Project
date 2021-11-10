package com.qa.springdemoproject.config;

import java.time.LocalTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

  @Bean
  public LocalTime timeNow() {

    return LocalTime.now();

  }


}
