package com.loja.backendlojanelio.config;

import com.loja.backendlojanelio.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService service;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        service.instantiateTestDatabase();
        return true;
    }
}
