package com.github.vishalm1029.HRMS.repositories

import com.github.vishalm1029.HRMS.entites.Attendance
import com.github.vishalm1029.HRMS.entites.Employee
import com.github.vishalm1029.HRMS.entites.PerformanceReview
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface PerformanceReviewRepo : JpaRepository<PerformanceReview, Long> {
    fun findByEmployee(employee: Employee): List<PerformanceReview>
    fun findByEmployeeAndReviewDateBetween(
        employee: Employee,
        start: LocalDate,
        end: LocalDate
    ): List<PerformanceReview>

}