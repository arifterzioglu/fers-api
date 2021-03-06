package com.metu.fers.application.controller

import com.metu.fers.application.service.ReservationService
import com.metu.fers.domain.entity.ReservationUpdateOutbox
import com.metu.fers.domain.model.request.reservation.CreateReservationRequest
import com.metu.fers.domain.model.request.reservation.EditReservationRequest
import com.metu.fers.domain.model.request.reservation.UpdateReservationEditRequest
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

    @PostMapping("/edit-reservation-request")
    @ResponseStatus(HttpStatus.OK)
    fun postReservationEditRequest(@RequestBody(required = true) editReservationRequest: EditReservationRequest): ResponseEntity<Any?> {
        reservationService.postReservationEditRequest(editReservationRequest)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/update-edit-request")
    @ResponseStatus(HttpStatus.OK)
    fun updateEditRequest(@RequestBody(required = true) updateReservationEditRequest: UpdateReservationEditRequest): ResponseEntity<Any?> {
        reservationService.updateEditRequest(updateReservationEditRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/freelancer-waiting-edit-reservation-requests")
    @ResponseStatus(HttpStatus.OK)
    fun getWaitingEditReservationRequestsByFreelancerId(
        @RequestParam(required = true) freelancerId: UUID,
    ): ResponseEntity<List<ReservationUpdateOutbox>> {
        return ResponseEntity.ok(
            reservationService.getWaitingEditReservationRequestsByFreelancerId(
                freelancerId
            )
        )
    }

    @GetMapping("/customer-waiting-edit-reservation-requests")
    @ResponseStatus(HttpStatus.OK)
    fun getWaitingEditReservationRequestsByCustomerId(
        @RequestParam(required = true) customerId: UUID,
    ): ResponseEntity<List<ReservationUpdateOutbox>> {
        return ResponseEntity.ok(
            reservationService.getWaitingEditReservationRequestsByCustomerId(
                customerId
            )
        )
    }
}