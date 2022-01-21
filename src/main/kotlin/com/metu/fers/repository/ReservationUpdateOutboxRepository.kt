package com.metu.fers.repository

import com.metu.fers.domain.entity.ReservationUpdateOutbox
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReservationUpdateOutboxRepository : JpaRepository<ReservationUpdateOutbox, UUID> {
    fun existsByReservationIdAndApprovalStatus(reservationId: UUID, approvalStatus: String): Boolean
}