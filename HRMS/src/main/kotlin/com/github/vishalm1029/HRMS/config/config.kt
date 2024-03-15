package com.github.vishalm1029.HRMS.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.modelmapper.ModelMapper


@Configuration
class config {
    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }
}