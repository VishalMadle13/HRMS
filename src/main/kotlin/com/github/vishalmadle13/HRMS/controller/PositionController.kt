package com.github.vishalmadle13.HRMS.controller

import com.github.vishalmadle13.HRMS.payloads.PerformanceReviewDto
import com.github.vishalmadle13.HRMS.payloads.PositionDto
import com.github.vishalmadle13.HRMS.services.PositionService
import com.github.vishalmadle13.HRMS.utils.ApiResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/position")
class PositionController (@Autowired val positionService: PositionService ) {

    // ----------------------------- Get All Positions ----------------------------
    @GetMapping("/")
    fun getAllPositions(): ResponseEntity<List<PositionDto>> {
        return ResponseEntity(
            this.positionService.getAllPositions(), HttpStatus.OK
        )
    }

    // -------------------------------- Get Position -------------------------------
    @GetMapping("/{positionId}")
    fun getPositionById(@PathVariable("positionId") positionId: String): ResponseEntity<PositionDto> {
        return ResponseEntity(
            this.positionService.getPositionById(positionId), HttpStatus.OK
        )
    }

    // -------------------------------- Add Position --------------------------------
    @PostMapping("/")
    fun addPosition( @RequestBody @Valid positionDto: PositionDto): ResponseEntity<PositionDto> {
        return ResponseEntity(
            this.positionService.addPosition(positionDto), HttpStatus.CREATED
        )
    }

    // -------------------------------- Update Position --------------------------------
    @PutMapping("/{positionId}")
    fun updatePosition(@PathVariable("positionId") positionId: String, @Valid @RequestBody positionDto: PositionDto): ResponseEntity<PositionDto> {
        if(positionId != positionDto.id) {
            var failResponse : MultiValueMap<String, String> = LinkedMultiValueMap()
            failResponse.add("message","Request-Body and Path-Variable is NOT Equal")
            failResponse.add("success","false")
            return ResponseEntity(PositionDto(),failResponse ,HttpStatus.NOT_MODIFIED)
        }
        return ResponseEntity(
            this.positionService.updatePosition(positionId, positionDto), HttpStatus.OK
        )
    }

    // -------------------------------- Delete Position --------------------------------
    @DeleteMapping("/{positionId}")
    fun deletePositionById(@PathVariable("positionId") positionId: String) : ResponseEntity<ApiResponse> {
        this.positionService.deletePosition(positionId)
        return ResponseEntity(
            ApiResponse("Position Deleted Successfully",true), HttpStatus.OK
        )
    }
}