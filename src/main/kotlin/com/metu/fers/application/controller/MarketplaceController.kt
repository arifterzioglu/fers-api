package com.metu.fers.application.controller

import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/marketplace")
class MarketplaceController {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    fun createReservation(): ResponseEntity<CreateReservationResponse> {
        return ResponseEntity.ok(CreateReservationResponse(id = "123"))
    }
}