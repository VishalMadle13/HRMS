package com.github.vishalmadle13.HRMS.controller

import com.github.vishalmadle13.HRMS.payloads.AttendanceDto
import com.github.vishalmadle13.HRMS.services.AttendanceService
import com.github.vishalmadle13.HRMS.utils.ApiResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/attendance")
class AttendanceController (
    @Autowired val attendanceService: AttendanceService
){
    // ----------------------------- Get All Attendance ----------------------------
    @GetMapping("/")
    fun getAllAttendances(): ResponseEntity<List<AttendanceDto>> {
        return ResponseEntity(
            this.attendanceService.getAllAttendance(),HttpStatus.OK
        )
    }

    // -------------------------------- Get Attendance By Id -------------------------------
    @GetMapping("/{attendanceId}")
    fun getAttendanceById(@PathVariable("attendanceId") attendanceId: Long): ResponseEntity<AttendanceDto> {
        return ResponseEntity(
            this.attendanceService.getAttendanceById(attendanceId), HttpStatus.OK
        )
    }

    // -------------------------------- Add Attendance --------------------------------
    @PostMapping("/")
    fun addAttendance( @RequestBody @Valid payrollDto: AttendanceDto): ResponseEntity<AttendanceDto> {
        return ResponseEntity(
            this.attendanceService.addAttendance(payrollDto), HttpStatus.CREATED
        )
    }

    // -------------------------------- Update Attendance --------------------------------
    @PutMapping("/{attendanceId}")
    fun updateAttendance(@PathVariable("attendanceId") attendanceId: Long, @Valid @RequestBody payrollDto: AttendanceDto): ResponseEntity<AttendanceDto> {
        return ResponseEntity(
            this.attendanceService.updateAttendance(attendanceId, payrollDto), HttpStatus.OK
        )
    }

    // -------------------------------- Delete Attendance --------------------------------
    @DeleteMapping("/{attendanceId}")
    fun deleteAttendanceById(@PathVariable("attendanceId") attendanceId: Long) : ResponseEntity<ApiResponse> {
        this.attendanceService.deleteAttendance(attendanceId)
        return ResponseEntity(
            ApiResponse("Attendance Deleted Successfully",true), HttpStatus.OK
        )
    }
}

