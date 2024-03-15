package com.github.vishalm1029.HRMS.repositories

import com.github.vishalm1029.HRMS.entites.Position
import org.springframework.data.jpa.repository.JpaRepository

interface PositionRepo : JpaRepository<Position, String> {

}