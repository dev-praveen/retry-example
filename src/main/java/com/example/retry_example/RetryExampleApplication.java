package com.example.retry_example;

import com.example.retry_example.service.RetryApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RetryExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(RetryExampleApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(RetryApiService retryApiService) {

    return args -> {
      final var message = retryApiService.sayHello(null);
      System.out.println(message.output() + "  ========  " + message.statusCode());
    };
  }
}
