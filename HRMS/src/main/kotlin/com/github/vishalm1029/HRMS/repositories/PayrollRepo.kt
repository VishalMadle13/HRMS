package com.github.vishalm1029.HRMS.repositories

import com.github.vishalm1029.HRMS.entites.Employee
import com.github.vishalm1029.HRMS.entites.Payroll
import org.springframework.data.jpa.repository.JpaRepository

interface PayrollRepo : JpaRepository<Payroll, Long> {
    fun findByEmployeeAndYearAndMonth(employee: Employee, year: Int, month: Int): List<Payroll>
}