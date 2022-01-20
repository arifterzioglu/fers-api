package com.metu.fers.domain.model.request.reservation

import java.util.*

class EditReservationRequest(
    var reservationId: UUID,
    val freelancerId: UUID,
    var timeslotId: Int,
    val reservationDate: String,
)