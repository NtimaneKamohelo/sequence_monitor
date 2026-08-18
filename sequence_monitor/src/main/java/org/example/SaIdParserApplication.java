package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the South African ID Parser Application
 *
 * @SpringBootApplication combines:
 *
 * @Configuration
 * @EnableAutoConfiguration
 * @ComponentScan
 */

@SpringBootApplication
public class SaIdParserApplication {
    public static void main(String[] args) {

        SpringApplication.run(
                SaIdParserApplication.class,
                args
        );
    }
}