package com.metu.fers.application.service

import com.metu.fers.domain.entity.Reservation
import com.metu.fers.domain.entity.Timeslot
import com.metu.fers.domain.model.request.reservation.CreateReservationRequest
import com.metu.fers.domain.model.request.timeslot.AddTimeslotRequest
import com.metu.fers.repository.ReservationRepository
import com.metu.fers.repository.TimeslotRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val timeslotRepository: TimeslotRepository,
    private val reservationRepository: ReservationRepository
) {
    fun addOrganization(addTimeslotRequest: AddTimeslotRequest) {
        timeslotRepository.save(
            Timeslot(
                startTime = addTimeslotRequest.startTime,
                endTime = addTimeslotRequest.endTime
            )
        )
    }

    fun createReservation(createReservationRequest: CreateReservationRequest) {
        //TODO: exception at eğer o saatte ve günde rez varsa.
        //Rez ekle.
        reservationRepository.save(Reservation())
    }
}