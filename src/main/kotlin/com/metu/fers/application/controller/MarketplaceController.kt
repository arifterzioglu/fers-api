package com.metu.fers.application.controller

import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/marketplace")
class MarketplaceController {

    @PostMapping("/add-service")
    @ResponseStatus(HttpStatus.OK)
    fun addService(): ResponseEntity<CreateReservationResponse> {
        return ResponseEntity.ok(CreateReservationResponse(id = "123"))
    }
}