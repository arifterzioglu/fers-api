package com.metu.fers.application.service

import com.metu.fers.domain.entity.Reservation
import com.metu.fers.domain.entity.ReservationUpdateOutbox
import com.metu.fers.domain.entity.Timeslot
import com.metu.fers.domain.model.request.reservation.CreateReservationRequest
import com.metu.fers.domain.model.request.reservation.EditReservationRequest
import com.metu.fers.domain.model.request.reservation.UpdateReservationEditRequest
import com.metu.fers.domain.model.request.timeslot.AddTimeslotRequest
import com.metu.fers.domain.model.response.reservation.*
import com.metu.fers.repository.ReservationRepository
import com.metu.fers.repository.ReservationUpdateOutboxRepository
import com.metu.fers.repository.TimeslotRepository
import org.springframework.stereotype.Service
import java.lang.management.RuntimeMXBean
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.transaction.Transactional
import kotlin.streams.toList

@Service
class ReservationService(
    private val timeslotRepository: TimeslotRepository,
    private val reservationRepository: ReservationRepository,
    private val reservationUpdateOutboxRepository: ReservationUpdateOutboxRepository
) {
    fun addOrganization(addTimeslotRequest: AddTimeslotRequest) {
        timeslotRepository.save(
            Timeslot(
                startTime = addTimeslotRequest.startTime,
                endTime = addTimeslotRequest.endTime
            )
        )
    }

    fun createReservation(createReservationRequest: CreateReservationRequest): UUID? {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val parsedDate = dateFormat.parse(createReservationRequest.reservationDate)
        reservationRepository.findByFreelancerIdAndReservationDateAndTimeslotId(
            createReservationRequest.freelancerId,
            Timestamp(parsedDate.time),
            createReservationRequest.timeslotId
        ).ifPresent { reservation -> throw RuntimeException("Freelancer already has a reservation in this time slot") }

        val save = reservationRepository.save(
            Reservation(
                freelancerId = createReservationRequest.freelancerId,
                customerId = createReservationRequest.customerId,
                serviceId = createReservationRequest.serviceId,
                reservationDate = Timestamp(parsedDate.time),
                timeslotId = createReservationRequest.timeslotId,
                createdDate = System.currentTimeMillis(),
                reservationStatus = "Created",
            )
        )
        return save.reservationId
    }

    @Transactional
    fun postReservationEditRequest(editReservationRequest: EditReservationRequest) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val parsedDate = dateFormat.parse(editReservationRequest.reservationDate)
        reservationRepository.findByFreelancerIdAndReservationDateAndTimeslotId(
            editReservationRequest.freelancerId,
            Timestamp(parsedDate.time),
            editReservationRequest.timeslotId
        ).ifPresent { throw RuntimeException("Freelancer already has a reservation in this time slot") }

        val reservation = reservationRepository.findById(editReservationRequest.reservationId)
        if (reservation.isEmpty) {
            throw RuntimeException("Reservation cannot be found")
        }

        if (reservationUpdateOutboxRepository.existsByReservationIdAndApprovalStatus(
                reservation.get().reservationId!!,
                "PENDING"
            )
        ) {
            throw RuntimeException("This reservation already has a waiting edit request!")
        }

        reservationUpdateOutboxRepository.save(
            ReservationUpdateOutbox(
                reservationId = reservation.get().reservationId,
                requestedReservationDate = Timestamp(parsedDate.time),
                requestedSlotId = editReservationRequest.timeslotId,
                requestOwner = editReservationRequest.requestOwner,
                approvalStatus = "PENDING",
                requestCreatedDate = Timestamp(System.currentTimeMillis())
            )
        )
    }

    @Transactional
    fun updateEditRequest(updateReservationEditRequest: UpdateReservationEditRequest) {
        val updateRequest = reservationUpdateOutboxRepository.findById(updateReservationEditRequest.requestId)
        if (updateRequest.isEmpty) {
            throw RuntimeException()
        }

        if (!updateRequest.get().approvalStatus.equals(
                "PENDING",
                ignoreCase = true
            )
        ) {
            throw RuntimeException(
                String.format(
                    "Request with id: %s is already %s",
                    updateReservationEditRequest.requestId,
                    updateRequest.get().approvalStatus.toString()
                )
            )
        }

        if (!updateReservationEditRequest.accepted) {
            updateRequest.get().approvalStatus = "REJECTED"
            reservationUpdateOutboxRepository.save(updateRequest.get())
            return
        }

        reservationRepository.findByFreelancerIdAndReservationDateAndTimeslotId(
            updateRequest.get().reservation!!.freelancerId!!,
            updateRequest.get().requestedReservationDate!!,
            updateRequest.get().requestedSlotId!!
        ).ifPresent { throw RuntimeException("Freelancer already has a reservation in this date and time slot") }

        val reservation = reservationRepository.findById(updateRequest.get().reservationId!!)
        if (reservation.isEmpty) {
            throw RuntimeException("Reservation could not be found")
        }

        reservation.get().updatedDate = System.currentTimeMillis()
        reservation.get().reservationDate = updateRequest.get().requestedReservationDate
        reservation.get().timeslotId = updateRequest.get().requestedSlotId

        reservationRepository.save(reservation.get())

        updateRequest.get().approvalStatus = "APPROVED"
        reservationUpdateOutboxRepository.save(updateRequest.get())
    }

    fun deleteReservation(reservationId: UUID) {
        reservationRepository.deleteById(reservationId)
    }

    fun getReservationsByCustomerId(customerId: UUID): List<GetReservationResponse>? {
        val findAllWithReservationTimeAfter =
            reservationRepository.findAllWithReservationTimeAfter(
                Timestamp(System.currentTimeMillis()),
                customerId
            )
                ?: emptyList()
        return mapReservations(findAllWithReservationTimeAfter)
    }

    fun getReservationsByFreelancerId(freelancerId: UUID): List<GetReservationResponse>? {
        val findAllWithReservationTimeAfter =
            reservationRepository.findAllWithReservationTimeAfterByFreelancerId(
                Timestamp(System.currentTimeMillis()),
                freelancerId
            ) ?: emptyList()
        return mapReservations(findAllWithReservationTimeAfter)
    }

    private fun mapReservations(findAllWithReservationTimeAfter: List<Reservation?>): List<GetReservationResponse> {
        return findAllWithReservationTimeAfter.stream().map {
            GetReservationResponse(
                reservationId = it!!.reservationId,
                reservationDate = it.reservationDate,
                createdDate = it.createdDate,
                reservationStatus = it.reservationStatus,
                timeslot = GetReservationTimeslot(startTime = it.timeslot!!.startTime, endTime = it.timeslot!!.endTime),
                customer = GetReservationCustomer(
                    customerId = it.customerId,
                    email = it.customer!!.email,
                    firstName = it.customer!!.firstName,
                    lastName = it.customer!!.lastName,
                    phoneNumber = it.customer!!.phoneNumber,
                    address = it.customer!!.address
                ),
                freelancer = GetReservationFreelancer(
                    freelancerId = it.freelancerId,
                    email = it.freelancer!!.email,
                    firstName = it.freelancer!!.firstName,
                    lastName = it.freelancer!!.lastName,
                    phoneNumber = it.freelancer!!.phoneNumber,
                ),
                marketplaceFreelancerService = GetReservationMarketplaceFreelancerService(
                    serviceId = it.serviceId,
                    serviceName = it.marketplaceFreelancerService!!.serviceName,
                    description = it.marketplaceFreelancerService!!.description
                )
            )
        }.toList()
    }
}