package com.metu.fers.repository

import com.metu.fers.domain.entity.Timeslot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimeslotRepository : JpaRepository<Timeslot, Int> {
}