package com.metu.fers.repository

import com.metu.fers.domain.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Repository
interface ReservationRepository : JpaRepository<Reservation, UUID> {
    fun findByFreelancerIdAndReservationDateAndTimeslotId(
        freelancerId: UUID,
        reservationDate: Timestamp,
        timeslotId: Int
    ): Optional<Reservation>

    @Query("select res from Reservation res where res.reservationDate >= :creationDateTime and res.customerId = :customerId")
    fun findAllWithReservationTimeAfter(
        @Param("creationDateTime") creationDateTime: Timestamp?,
        @Param("customerId") customerId: UUID
    ): List<Reservation?>?

    @Query("select res from Reservation res where res.reservationDate >= :creationDateTime and res.freelancerId = :freelancerId")
    fun findAllWithReservationTimeAfterByFreelancerId(
        @Param("creationDateTime") creationDateTime: Timestamp?,
        @Param("freelancerId") freelancerId: UUID
    ): List<Reservation?>?

    @Query("select res from Reservation res where res.reservationDate >= :startDate and res.reservationDate <= :endDate and res.freelancerId = :freelancerId")
    fun findAllWithStartDateAndEndDateAndFreelancerId(
        @Param("startDate") startDate: Timestamp,
        @Param("endDate") endDate: Timestamp,
        @Param("freelancerId") freelancerId: UUID
    ): List<Reservation?>?
}