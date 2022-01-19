package com.metu.fers.repository

import com.metu.fers.domain.entity.Admin
import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReservationRepository : JpaRepository<Reservation, UUID> {
}