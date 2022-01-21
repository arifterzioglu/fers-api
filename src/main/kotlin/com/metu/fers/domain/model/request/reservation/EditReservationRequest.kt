package com.metu.fers.domain.model.request.reservation

import java.util.*

class EditReservationRequest(
    var reservationId: UUID,
    val freelancerId: UUID,
    val customerId: UUID,
    var timeslotId: Int,
    val reservationDate: String,
    val requestOwner: String,
)