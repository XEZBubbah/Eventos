package com.example.eventos.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfiguration {

    @Bean
    public ModelMapper obtenerMapper() {
        return new ModelMapper();
    }
}
