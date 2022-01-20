package com.metu.fers.application.controller

import com.metu.fers.application.service.ReservationService
import com.metu.fers.domain.model.request.reservation.CreateReservationRequest
import com.metu.fers.domain.model.request.reservation.EditReservationRequest
import com.metu.fers.domain.model.request.timeslot.AddTimeslotRequest
import com.metu.fers.domain.model.response.reservation.GetReservationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/reservation")
class ReservationController(private val reservationService: ReservationService) {
    @PostMapping("/add-timeslot")
    @ResponseStatus(HttpStatus.OK)
    fun addTimeslot(@RequestBody(required = true) addTimeslotRequest: AddTimeslotRequest): ResponseEntity<Any?> {
        reservationService.addOrganization(addTimeslotRequest)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/create-reservation")
    @ResponseStatus(HttpStatus.OK)
    fun createReservation(@RequestBody(required = true) createReservationRequest: CreateReservationRequest): ResponseEntity<UUID> {
        return ResponseEntity.ok(reservationService.createReservation(createReservationRequest))
    }

    @PutMapping("/edit-reservation")
    @ResponseStatus(HttpStatus.OK)
    fun editReservation(@RequestBody(required = true) editReservationRequest: EditReservationRequest): ResponseEntity<Any?> {
        reservationService.editReservation(editReservationRequest)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/delete-reservation")
    @ResponseStatus(HttpStatus.OK)
    fun deleteReservation(@RequestParam(required = true) reservationId: UUID): ResponseEntity<Any?> {
        reservationService.deleteReservation(reservationId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/customer-reservations")
    @ResponseStatus(HttpStatus.OK)
    fun getCustomerReservations(@RequestParam(required = true) customerId: UUID): ResponseEntity<List<GetReservationResponse>>? {
        return ResponseEntity.ok(reservationService.getReservationsByCustomerId(customerId))
    }

    @GetMapping("/freelancer-reservations")
    @ResponseStatus(HttpStatus.OK)
    fun getFreelancerReservations(@RequestParam(required = true) freelancerId: UUID): ResponseEntity<List<GetReservationResponse>>? {
        return ResponseEntity.ok(reservationService.getReservationsByFreelancerId(freelancerId))
    }
}