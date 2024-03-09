package com.github.vishalmadle13.HRMS.services

import com.github.vishalmadle13.HRMS.entites.Attendance
import org.springframework.stereotype.Service

@Service
interface AttendanceService {
    fun getAllAttendance() : List<Attendance>
    fun getAttendanceById(attendanceId : Long) : Attendance ?
    fun addAttendance(attendance: Attendance) : Attendance
    fun updateAttendance(attendanceId: Long,attendance: Attendance)  : Attendance
    fun deleteAttendance(attendanceId: Long)
}