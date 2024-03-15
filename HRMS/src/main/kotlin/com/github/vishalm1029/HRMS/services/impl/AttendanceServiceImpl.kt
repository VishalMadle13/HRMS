package com.github.vishalm1029.HRMS.services.impl


import com.github.vishalm1029.HRMS.entites.Attendance
import com.github.vishalm1029.HRMS.entites.Employee
import com.github.vishalm1029.HRMS.exceptions.ResourceAlreadyExistException
import com.github.vishalm1029.HRMS.exceptions.ResourceNotFoundException
import com.github.vishalm1029.HRMS.payloads.attendance.AttendanceDto
import com.github.vishalm1029.HRMS.payloads.attendance.MonthlyAttendanceDto
import com.github.vishalm1029.HRMS.repositories.AttendanceRepo
import com.github.vishalm1029.HRMS.repositories.EmployeeRepo
import com.github.vishalm1029.HRMS.services.AttendanceService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.Objects
import java.util.stream.Collectors

@Component
class AttendanceServiceImpl(
    @Autowired val attendanceRepo: AttendanceRepo,
    @Autowired val mapper: ModelMapper,
    @Autowired val employeeRepo: EmployeeRepo
) : AttendanceService {
    override fun getAllAttendance(): List<AttendanceDto> {
        return attendanceRepo
            .findAll()
            .stream()
            .map { attendance: Attendance ->
                mapper.map(attendance, AttendanceDto::class.java)
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
        val employee: Employee = employeeRepo
            .findById(attendanceDto.employee.id!!)
            .orElseThrow {
                ResourceNotFoundException("Employee", "Id", attendanceDto.employee.id)
            }
        if (!attendanceRepo.findByEmployeeAndDate(employee, attendanceDto.date).isEmpty()) {
            throw ResourceAlreadyExistException("Attendance")
        }
        var attendance: Attendance = mapper.map(attendanceDto, Attendance::class.java)
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
        var attendance: Attendance = mapper.map(attendanceDto, Attendance::class.java)
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

    override fun getAttendanceByEmployeeAndMonth(employeeId: Long, year: Int, month: Int): Any {
        val startOfMonth = LocalDate.of(year, month, 1)
        val endOfMonth = startOfMonth.plusMonths(1).minusDays(1)

        val employee: Employee =
            employeeRepo
                .findById(employeeId)
                .orElseThrow {
                    ResourceNotFoundException("Employee", "Id", employeeId)
                }
        var attendanceRecords: List<Attendance> =
            attendanceRepo
                .findByEmployeeAndDateBetween(
                    employee, startOfMonth, endOfMonth
                )
        var monthlyAttendanceDtos: List<MonthlyAttendanceDto> = ArrayList()
        for (it in attendanceRecords) {
            monthlyAttendanceDtos
                .addLast(
                    MonthlyAttendanceDto(it.date, it.clockInTime, it.clockOutTime)
                )
        }
        return object {
            val employeeId: Long = employeeId
            val firstName: String = employee.firstName
            val lastName: String = employee.lastName
            val department: String = employee.department.department
            val position: String = employee.position.title
            val attendedDays: Int = monthlyAttendanceDtos.size
            val attendance: List<MonthlyAttendanceDto> = monthlyAttendanceDtos
        }
    }


}

