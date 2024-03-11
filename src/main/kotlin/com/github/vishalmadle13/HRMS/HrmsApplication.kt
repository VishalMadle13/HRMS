package com.github.vishalmadle13.HRMS

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class HrmsApplication

fun main(args: Array<String>) {
	runApplication<HrmsApplication>(*args)
}
