package com.metu.fers.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Timeslot")
open class Timeslot(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timeslot_id")
    open var timeslotId: Int? = null,

    @Column(name = "start_time", unique = true)
    open var startTime: String? = null,

    @Column(name = "end_time")
    open var endTime: String? = null,
)