package com.github.vishalm1029.HRMS.services.impl

import com.github.vishalm1029.HRMS.entites.Position
import com.github.vishalm1029.HRMS.exceptions.ResourceAlreadyExistException
import com.github.vishalm1029.HRMS.exceptions.ResourceNotFoundException
import com.github.vishalm1029.HRMS.payloads.PositionDto
import com.github.vishalm1029.HRMS.repositories.EmployeeRepo
import com.github.vishalm1029.HRMS.repositories.PositionRepo
import com.github.vishalm1029.HRMS.services.PositionService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.concurrent.thread

@Service
@Component
class PositionServiceImpl(
    @Autowired val positionRepo: PositionRepo,
    @Autowired val modelMapper: ModelMapper,
    @Autowired val employeeRepo: EmployeeRepo
) : PositionService {
    override fun getAllPositions(): List<PositionDto> {
        return positionRepo
            .findAll()
            .stream()
            .map { position: Position ->
                this.modelMapper.map(position, PositionDto::class.java)
            }
            .collect(Collectors.toList())
    }

    override fun getPositionById(positionId: String): PositionDto? {
        val positionEntity: Position = positionRepo
            .findById(positionId)
            .orElseThrow {
                ResourceNotFoundException("Position", "Id", positionId)
            }
        return modelMapper.map(
            positionEntity, PositionDto::class.java
        )
    }

    override fun addPosition(positionDto: PositionDto): PositionDto {
        val position: Position = modelMapper.map(positionDto, Position::class.java)
        if (positionRepo.findById(position.id).isPresent) throw ResourceAlreadyExistException("Position")
        return modelMapper.map(
            positionRepo.save(position), PositionDto::class.java
        )
    }

    override fun updatePosition(positionId: String, positionDto: PositionDto): PositionDto {
        positionRepo
            .findById(positionId)
            .orElseThrow {
                ResourceNotFoundException("Position", "Id", positionId)
            }
        val updatedPosition: Position = modelMapper.map(positionDto, Position::class.java)
        updatedPosition.id = positionId

        return modelMapper.map(
            positionRepo.save(updatedPosition), PositionDto::class.java
        )
    }

    override fun deletePosition(positionId: String) {
        positionRepo
            .findById(positionId)
            .orElseThrow {
                ResourceNotFoundException("Position", "Id", positionId)
            }
        positionRepo.deleteById(positionId)
    }


}