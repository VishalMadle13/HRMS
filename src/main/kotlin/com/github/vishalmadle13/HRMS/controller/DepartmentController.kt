package com.github.vishalmadle13.HRMS.controller

import com.github.vishalmadle13.HRMS.payloads.DepartmentDto
import com.github.vishalmadle13.HRMS.services.DepartmentService
import com.github.vishalmadle13.HRMS.utils.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/department")
class DepartmentController (@Autowired val departmentService: DepartmentService){

    // ---- Get All Departments
    @GetMapping("/")
    fun getAllDepartments(): ResponseEntity<List<DepartmentDto>>  = ResponseEntity(this.departmentService.getAllDepartments(), HttpStatus.OK)

    // ---- Get DepartmentDto
    @GetMapping("/{departmentId}")
    fun getDepartmentById(@PathVariable("departmentId") departmentId : String) : ResponseEntity<DepartmentDto> = ResponseEntity(this.departmentService.getDepartmentById(departmentId) , HttpStatus.OK )

    // ---- Add DepartmentDto
    @PostMapping("/")
    fun addDepartment(@RequestBody departmentDto: DepartmentDto ) : ResponseEntity<DepartmentDto> = ResponseEntity(this.departmentService.addDepartment(departmentDto), HttpStatus.CREATED)

    // ---- Update DepartmentDto
    @PutMapping("/{departmentId}")
    fun updateDepartment(@PathVariable("departmentId") departmentId: String, @RequestBody departmentDto: DepartmentDto): ResponseEntity<DepartmentDto> = ResponseEntity(this.departmentService.updateDepartment(departmentId,departmentDto), HttpStatus.OK)

    // ---- Delete DepartmentDto
    @DeleteMapping("/{departmentId}")
    fun deleteDepartmentById(@PathVariable("departmentId") departmentId: String) : ResponseEntity<ApiResponse> {
        this.departmentService.deleteDepartmentById(departmentId)
        return ResponseEntity(ApiResponse("Department Deleted Successfully", true), HttpStatus.OK)
    }
}