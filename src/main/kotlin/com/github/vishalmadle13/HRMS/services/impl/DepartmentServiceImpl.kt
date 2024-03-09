package com.github.vishalmadle13.HRMS.services.impl

import com.github.vishalmadle13.HRMS.entites.Department
import com.github.vishalmadle13.HRMS.exceptions.ResourceNotFoundException
import com.github.vishalmadle13.HRMS.payloads.DepartmentDto
import com.github.vishalmadle13.HRMS.repositories.DepartmentRepo
import com.github.vishalmadle13.HRMS.services.DepartmentService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
@Component
class DepartmentServiceImpl(
    @Autowired val departmentRepo: DepartmentRepo,
    @Autowired val modelMapper : ModelMapper
)  : DepartmentService
{
    override fun getAllDepartments() : List<DepartmentDto>   {
        return  this.departmentRepo.findAll().stream().map { department ->  modelMapper.map(department, DepartmentDto::class.java)}.collect(Collectors.toList())
    }
    override fun getDepartmentById(departmentId : String) : DepartmentDto {
        val departmentEntity : Department =  departmentRepo.findById(departmentId).orElseThrow{ResourceNotFoundException("Department","Id", departmentId )}
        return this.modelMapper.map(departmentEntity,DepartmentDto::class.java)
    }
    override fun updateDepartment(departmentId: String, departmentDto: DepartmentDto) : DepartmentDto ? {
        departmentRepo.findById(departmentId).orElseThrow{ResourceNotFoundException("Department","Id",departmentId )}
        var updatedDepartment : Department = modelMapper.map(departmentDto,Department::class.java)
        updatedDepartment.id = departmentId
        return this.modelMapper.map(departmentRepo.save(updatedDepartment),DepartmentDto::class.java)
    }
    override fun addDepartment(departmentDto: DepartmentDto) : DepartmentDto {
         val departmentEntity : Department = modelMapper.map(departmentDto, Department::class.java)
         return modelMapper.map(departmentRepo.save(departmentEntity) ,DepartmentDto::class.java)
    }
    override fun deleteDepartmentById(departmentId : String)  {
        departmentRepo.findById(departmentId).orElseThrow{ResourceNotFoundException("Department", "Id", departmentId )}
        departmentRepo.deleteById(departmentId)
    }
}