package com.github.vishalm1029.HRMS.repositories

import com.github.vishalm1029.HRMS.entites.Attendance
import com.github.vishalm1029.HRMS.entites.Employee
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface AttendanceRepo : JpaRepository<Attendance, Long> {
    fun findByEmployeeAndDate(employee: Employee, date: LocalDate): List<Attendance>
    fun findByEmployeeAndDateBetween(employee: Employee, start: LocalDate, end: LocalDate): List<Attendance>

}
