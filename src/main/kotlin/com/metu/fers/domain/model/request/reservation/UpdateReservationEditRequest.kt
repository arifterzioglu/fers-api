package com.metu.fers.domain.model.request.reservation

import java.util.*

class UpdateReservationEditRequest(
    var requestId: UUID,
    val accepted: Boolean,
)