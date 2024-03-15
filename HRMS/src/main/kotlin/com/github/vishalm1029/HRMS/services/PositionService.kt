package com.github.vishalm1029.HRMS.services

import com.github.vishalm1029.HRMS.payloads.PositionDto
import org.springframework.stereotype.Service

@Service
interface PositionService {
    fun getAllPositions(): List<PositionDto>
    fun getPositionById(positionId: String): PositionDto?
    fun addPosition(positionDto: PositionDto): PositionDto
    fun updatePosition(positionId: String, positionDto: PositionDto): PositionDto?
    fun deletePosition(positionId: String)
}