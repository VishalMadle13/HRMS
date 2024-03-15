package com.github.vishalm1029.HRMS.repositories

import com.github.vishalm1029.HRMS.entites.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepo : JpaRepository<Employee, Long> {
}