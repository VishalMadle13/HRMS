package com.github.vishalmadle13.HRMS.services

import com.github.vishalmadle13.HRMS.payloads.DepartmentDto
import org.springframework.stereotype.Service
import java.util.*

@Service
interface DepartmentService {
    fun getAllDepartments() : List<DepartmentDto>
    fun getDepartmentById(departmentId : String) : DepartmentDto?
    fun addDepartment(departmentDto: DepartmentDto) : DepartmentDto
    fun updateDepartment(departmentId: String, departmentDto: DepartmentDto) : DepartmentDto ?
    fun deleteDepartmentById(departmentId: String)
}