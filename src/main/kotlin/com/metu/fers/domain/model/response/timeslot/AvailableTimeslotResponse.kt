package com.metu.fers.domain.model.response.timeslot

import com.metu.fers.domain.entity.Timeslot
import java.sql.Timestamp

class AvailableTimeslotResponse(
    private val date: Timestamp,
    private val slotList: List<Timeslot>
)