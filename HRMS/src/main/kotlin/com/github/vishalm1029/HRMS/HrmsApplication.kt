package com.github.vishalm1029.HRMS

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication

class HrmsApplication

fun main(args: Array<String>) {
    runApplication<HrmsApplication>(*args)
}
