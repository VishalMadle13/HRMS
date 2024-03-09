package com.github.vishalmadle13.HRMS.repositories

import com.github.vishalmadle13.HRMS.entites.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepo : JpaRepository<Employee, Long> {
}