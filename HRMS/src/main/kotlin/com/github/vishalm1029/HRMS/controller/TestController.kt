package com.github.vishalm1029.HRMS.controller

import com.github.vishalm1029.HRMS.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/test")
//@CrossOrigin(origins = "http://localhost:4200")
class TestController(
    @Autowired val employeeService: EmployeeService
) {

    @GetMapping("")
    fun publicAccess(): Any {

        return employeeService.getAllEmployees()
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    fun test(): String? {
        return "test Working"
    }

}