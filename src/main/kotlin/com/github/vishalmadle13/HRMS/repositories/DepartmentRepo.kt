package com.github.vishalmadle13.HRMS.repositories

import com.github.vishalmadle13.HRMS.entites.Department
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface DepartmentRepo : JpaRepository<Department, String> {
}