package com.github.vishalmadle13.HRMS.repositories

import com.github.vishalmadle13.HRMS.entites.Attendance
import org.springframework.data.jpa.repository.JpaRepository

interface AttendanceRepo : JpaRepository<Attendance, Long> {
}