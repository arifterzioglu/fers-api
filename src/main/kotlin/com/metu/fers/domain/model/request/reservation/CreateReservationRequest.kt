package com.metu.fers.domain.model.request.reservation

import java.util.*

class CreateReservationRequest(
    val reservationNumber: UUID,
    val customerId: UUID,
    val freelancerId: UUID,
    val serviceId: Int,
    var timeslotId: Int,
    val reservationDate: String,
)