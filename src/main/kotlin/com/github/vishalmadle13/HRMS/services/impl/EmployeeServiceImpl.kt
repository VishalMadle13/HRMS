package com.github.vishalmadle13.HRMS.services.impl

 import com.github.vishalmadle13.HRMS.entites.Department
 import com.github.vishalmadle13.HRMS.entites.Employee
 import com.github.vishalmadle13.HRMS.entites.Position
 import com.github.vishalmadle13.HRMS.exceptions.ResourceNotFoundException
 import com.github.vishalmadle13.HRMS.payloads.EmployeeDto
 import com.github.vishalmadle13.HRMS.repositories.DepartmentRepo
 import com.github.vishalmadle13.HRMS.repositories.EmployeeRepo
 import com.github.vishalmadle13.HRMS.repositories.PositionRepo
 import com.github.vishalmadle13.HRMS.services.EmployeeService
 import org.modelmapper.ModelMapper
 import org.springframework.beans.factory.annotation.Autowired
 import org.springframework.data.domain.Example
 import org.springframework.stereotype.Component
 import org.springframework.stereotype.Service
 import java.util.Objects
 import java.util.Optional
 import java.util.stream.Collectors

@Service
@Component
class EmployeeServiceImpl (
    @Autowired val employeeRepo : EmployeeRepo,
    @Autowired val modelMapper: ModelMapper,
    @Autowired val positionRepo: PositionRepo,
    @Autowired val departmentRepo: DepartmentRepo
) : EmployeeService{
    override fun getAllEmployees() : List<EmployeeDto> {
        return employeeRepo
            .findAll()
            .stream()
            .map {
                employee-> modelMapper.map(employee,EmployeeDto::class.java)
            }
            .collect(Collectors.toList())
    }
    override fun getEmployeeById(employeeId: Long) : EmployeeDto? {
        val employeeEntity:Employee = employeeRepo
            .findById(employeeId)
                .orElseThrow{
                    ResourceNotFoundException("Employee","Id", employeeId )
                }

        return modelMapper.map(
            employeeEntity,EmployeeDto::class.java
        )
    }

    override fun addEmployee(employeeDto: EmployeeDto) : EmployeeDto {
        var employeeEntity : Employee = modelMapper.map(employeeDto, Employee::class.java)
        val position : Position = positionRepo
            .findById(employeeEntity.position.id)
                .orElseThrow{
                    ResourceNotFoundException("Position","Id",employeeEntity.position.id )
                }
        val department : Department = departmentRepo
            .findById(employeeEntity.department.id)
                .orElseThrow{
                    ResourceNotFoundException("Department","Id",employeeEntity.department.id )
                }

        employeeEntity.position = position
        employeeEntity.department = department
        val savedEmployee : Employee = employeeRepo.save(employeeEntity)
        department.employees.addLast(savedEmployee)
        position.employees.addLast(savedEmployee)

        return modelMapper.map(
            savedEmployee,EmployeeDto::class.java
        )
    }

    override fun updateEmployee(employeeId: Long, employeeDto: EmployeeDto) : EmployeeDto ? {
        employeeRepo
            .findById(employeeId)
                .orElseThrow{
                    ResourceNotFoundException("Employee","Id",employeeId )
                }
        var updatedEmployee : Employee = modelMapper.map(employeeDto,Employee::class.java)

        updatedEmployee.position = positionRepo
            .findById(updatedEmployee.position.id)
                .orElseThrow{
                    ResourceNotFoundException("Position","Id",updatedEmployee.position.id)
                }
        updatedEmployee.department = departmentRepo
            .findById(updatedEmployee.department.id)
                .orElseThrow {
                    ResourceNotFoundException("Department","Id",updatedEmployee.department.id)
                }

        return modelMapper.map(
            employeeRepo.save(updatedEmployee),EmployeeDto::class.java
        )
    }
    override fun deleteEmployee(employeeId: Long) {
        employeeRepo
            .findById(employeeId)
                .orElseThrow {
                    ResourceNotFoundException("Employee","Id", employeeId )
                }
        employeeRepo.deleteById(employeeId)
    }
}

