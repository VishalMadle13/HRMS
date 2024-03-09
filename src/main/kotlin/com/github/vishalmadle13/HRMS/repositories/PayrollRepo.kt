package com.github.vishalmadle13.HRMS.repositories

import com.github.vishalmadle13.HRMS.entites.Payroll
import org.springframework.data.jpa.repository.JpaRepository

interface PayrollRepo : JpaRepository<Payroll , Long> {
}