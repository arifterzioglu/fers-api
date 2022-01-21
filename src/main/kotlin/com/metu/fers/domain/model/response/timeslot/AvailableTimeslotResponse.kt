package com.metu.fers.domain.model.response.timeslot

data class AvailableTimeslotResponse(
    val date: String,
    val availableSlotList: List<GetTimeslotResponse>
)

data class GetTimeslotResponse(
    var timeslotId: Int? = null,
    var startTime: String? = null,
    var endTime: String? = null,
)