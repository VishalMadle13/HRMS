package com.github.vishalmadle13.HRMS.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class config {
    @Bean
    fun modelMapper(): ModelMapper? {
        return ModelMapper()
    }
}