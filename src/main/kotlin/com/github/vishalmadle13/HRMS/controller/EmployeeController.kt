package com.github.vishalmadle13.HRMS.controller

import com.github.vishalmadle13.HRMS.payloads.EmployeeDto
import com.github.vishalmadle13.HRMS.payloads.PositionDto
import com.github.vishalmadle13.HRMS.services.EmployeeService
import com.github.vishalmadle13.HRMS.utils.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employee")
class EmployeeController (@Autowired val employeeService: EmployeeService){
    // -------------------------------- Get All Employees --------------------------------
    @GetMapping("/")
    fun getAllUsers() : ResponseEntity<List<EmployeeDto>> {
        return ResponseEntity(
            this.employeeService.getAllEmployees(),HttpStatus.OK
        )
    }

    // -------------------------------- Get Employee By Id --------------------------------
    @GetMapping("/{employeeId}")
    fun getEmployeeById(@PathVariable("employeeId") employeeId : Long) : ResponseEntity<EmployeeDto> {
        return ResponseEntity(
            this.employeeService.getEmployeeById(employeeId), HttpStatus.OK
        )
    }

    // -------------------------------- Add Employee ----- --------------------------------
    @PostMapping("/")
    fun addEmployee(@RequestBody employee: EmployeeDto) : ResponseEntity<EmployeeDto> {
        return ResponseEntity(
            this.employeeService.addEmployee(employee), HttpStatus.CREATED
        )
    }

    // -------------------------------- Update Employee ------------------------------------
    @PutMapping("/{employeeId}")
    fun updateEmployee(@PathVariable("employeeId") employeeId: Long, @RequestBody employee: EmployeeDto) : ResponseEntity<EmployeeDto> {
        if(employeeId != employee.id) {
            var failResponse : MultiValueMap<String, String> = LinkedMultiValueMap()
            failResponse.add("message","Request-Body and Path-Variable is NOT Equal")
            failResponse.add("success","false")
            return ResponseEntity(EmployeeDto(),failResponse ,HttpStatus.NOT_MODIFIED)
        }
        return ResponseEntity(
            this.employeeService.updateEmployee(employeeId,employee), HttpStatus.OK
        )
    }

    // -------------------------------- Delete Employee ------------------------------------
    @DeleteMapping("/{employeeId}")
    fun deleteEmployee(@PathVariable("employeeId") employeeId: Long) : ResponseEntity<ApiResponse> {
        this.employeeService.deleteEmployee(employeeId)
        return ResponseEntity(ApiResponse(
            "Employee Deleted Successfully", true), HttpStatus.OK
        )
    }
}