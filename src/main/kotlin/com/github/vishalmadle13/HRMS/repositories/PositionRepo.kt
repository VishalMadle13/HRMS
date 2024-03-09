package com.github.vishalmadle13.HRMS.repositories

import com.github.vishalmadle13.HRMS.entites.Position
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PositionRepo : JpaRepository<Position, String> {
}