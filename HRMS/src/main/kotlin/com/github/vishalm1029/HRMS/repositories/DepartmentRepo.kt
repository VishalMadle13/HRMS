package com.github.vishalm1029.HRMS.repositories

import com.github.vishalm1029.HRMS.entites.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepo : JpaRepository<Department, String> {
}