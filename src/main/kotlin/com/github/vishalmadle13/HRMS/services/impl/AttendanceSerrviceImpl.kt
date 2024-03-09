package com.github.vishalmadle13.HRMS.services.impl

import com.github.vishalmadle13.HRMS.entites.Attendance
import com.github.vishalmadle13.HRMS.repositories.AttendanceRepo
import org.springframework.beans.factory.annotation.Autowired

class AttendanceSerrviceImpl (@Autowired val attendanceRepo : AttendanceRepo) {
    fun getAllAttendance(): List<Attendance> = attendanceRepo.findAll()
    fun getAttendanceById(attendanceId : Long) : Attendance  = attendanceRepo.findById(attendanceId).orElse(null)
    fun addAttendance(attendance: Attendance) : Attendance = attendanceRepo.save(attendance)
    fun updateAttendance(attendanceId: Long, attendance: Attendance) : Attendance = attendanceRepo.save(attendance)
    fun deleteAttendance(attendanceId: Long) = attendanceRepo.deleteById(attendanceId)
}