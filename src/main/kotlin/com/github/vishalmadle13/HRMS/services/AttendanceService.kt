package com.github.vishalmadle13.HRMS.services

 import com.github.vishalmadle13.HRMS.payloads.AttendanceDto
import org.springframework.stereotype.Service

@Service
interface AttendanceService {
    fun getAllAttendance() : List<AttendanceDto>
    fun getAttendanceById(attendanceId : Long) : AttendanceDto ?
    fun addAttendance(attendanceDto: AttendanceDto) : AttendanceDto
    fun updateAttendance(attendanceId: Long,attendanceDto: AttendanceDto)  : AttendanceDto
    fun deleteAttendance(attendanceId: Long)
}