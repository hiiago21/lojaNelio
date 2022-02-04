package com.loja.backendlojanelio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class BackendLojaNelioApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(BackendLojaNelioApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {


    }
}
