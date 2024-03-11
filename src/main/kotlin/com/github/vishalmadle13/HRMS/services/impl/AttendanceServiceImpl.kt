package com.github.vishalmadle13.HRMS.services.impl

import com.github.vishalmadle13.HRMS.entites.Attendance
import com.github.vishalmadle13.HRMS.entites.Employee
import com.github.vishalmadle13.HRMS.entites.Payroll
import com.github.vishalmadle13.HRMS.entites.PerformanceReview
import com.github.vishalmadle13.HRMS.exceptions.ResourceNotFoundException
import com.github.vishalmadle13.HRMS.payloads.AttendanceDto
import com.github.vishalmadle13.HRMS.payloads.PayrollDto
import com.github.vishalmadle13.HRMS.repositories.AttendanceRepo
import com.github.vishalmadle13.HRMS.repositories.EmployeeRepo
 import com.github.vishalmadle13.HRMS.services.AttendanceService
 import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class AttendanceServiceImpl (
    @Autowired val attendanceRepo : AttendanceRepo,
    @Autowired val mapper : ModelMapper,
    @Autowired val employeeRepo: EmployeeRepo
) : AttendanceService {
    override fun getAllAttendance(): List<AttendanceDto> {
        return attendanceRepo
            .findAll()
                .stream()
                    .map { position ->
                        this.mapper.map(position, AttendanceDto::class.java)
                    }
                    .collect(Collectors.toList())
    }

    override fun getAttendanceById(attendanceId: Long): AttendanceDto? {
        val positionEntity: Attendance = attendanceRepo
            .findById(attendanceId)
                .orElseThrow {
                    ResourceNotFoundException("Attendance", "Id", attendanceId)
                }
        return mapper.map(
            positionEntity, AttendanceDto::class.java
        )
    }

    override fun addAttendance(attendanceDto: AttendanceDto): AttendanceDto {
        var attendance: Attendance = mapper.map(attendanceDto, Attendance::class.java)
        val employee : Employee = employeeRepo
            .findById(attendance.employee.id!!)
            .orElseThrow{
                ResourceNotFoundException("Employee","Id",attendance.employee.id)
            }
        attendance.employee = employee
        return mapper.map(
            attendanceRepo.save(attendance), AttendanceDto::class.java
        )
    }

    override fun updateAttendance(attendanceId: Long, attendanceDto: AttendanceDto): AttendanceDto {
        attendanceRepo
            .findById(attendanceId)
                .orElseThrow {
                    ResourceNotFoundException("Attendance", "Id", attendanceId)
                }
        val employee: Employee = employeeRepo
            .findById(attendanceDto.employee.id!!)
                .orElseThrow {
                    ResourceNotFoundException("Employee", "Id", attendanceDto.employee.id)
                }
        var attendance: Attendance = mapper.map(attendanceDto,Attendance::class.java)
        attendance.employee = employee
        attendance.id = attendanceId
        
        return mapper.map(
            attendanceRepo.save(attendance), AttendanceDto::class.java
        )
    }

    override fun deleteAttendance(attendanceId: Long) {
        attendanceRepo
            .findById(attendanceId)
                .orElseThrow {
                    ResourceNotFoundException("Attendance", "Id", attendanceId)
                }
        attendanceRepo.deleteById(attendanceId)
    }
}

