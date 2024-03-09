package com.github.vishalmadle13.HRMS.services

 import com.github.vishalmadle13.HRMS.payloads.EmployeeDto
import org.springframework.stereotype.Service

@Service
interface EmployeeService {
    fun getAllEmployees() : List<EmployeeDto>
    fun getEmployeeById(employeeId : Long) : EmployeeDto ?
    fun addEmployee(employee: EmployeeDto) : EmployeeDto
    fun updateEmployee(employeeId: Long, updatedEmployee: EmployeeDto) : EmployeeDto ?
    fun deleteEmployee(employeeId: Long)
}