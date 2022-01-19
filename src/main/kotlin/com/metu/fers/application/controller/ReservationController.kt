package com.metu.fers.application.controller

import com.metu.fers.application.service.ReservationService
import com.metu.fers.domain.model.request.reservation.CreateReservationRequest
import com.metu.fers.domain.model.request.timeslot.AddTimeslotRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reservation")
class ReservationController(private val reservationService: ReservationService) {
    @PostMapping("/add-timeslot")
    @ResponseStatus(HttpStatus.OK)
    fun addTimeslot(@RequestBody(required = true) addTimeslotRequest: AddTimeslotRequest): ResponseEntity<Any?> {
        reservationService.addOrganization(addTimeslotRequest)
        return ResponseEntity.ok().build()
    }

    //add reservation
    @PostMapping("/create-reservation")
    @ResponseStatus(HttpStatus.OK)
    fun createReservation(@RequestBody(required = true) createReservationRequest: CreateReservationRequest): ResponseEntity<Any?> {
        reservationService.createReservation(createReservationRequest)
        return ResponseEntity.ok().build()
    }

    //edit reservation

    //Delete reservation
}