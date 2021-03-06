package com.metu.fers.application.service

import com.metu.fers.domain.entity.Freelancer
import com.metu.fers.domain.entity.FreelancerScore
import com.metu.fers.domain.entity.Reservation
import com.metu.fers.domain.entity.Timeslot
import com.metu.fers.domain.exception.CustomerAlreadyCreatedException
import com.metu.fers.domain.exception.FreelancerNotFoundException
import com.metu.fers.domain.exception.PasswordDoesNotMatchException
import com.metu.fers.domain.model.request.freelancer.CreateFreelancerRequest
import com.metu.fers.domain.model.request.freelancer.LogInFreelancerRequest
import com.metu.fers.domain.model.request.freelancer.ScoreFreelancerRequest
import com.metu.fers.domain.model.response.timeslot.AvailableTimeslotResponse
import com.metu.fers.domain.model.response.timeslot.GetTimeslotResponse
import com.metu.fers.repository.FreelancerRepository
import com.metu.fers.repository.FreelancerScoreRepository
import com.metu.fers.repository.ReservationRepository
import com.metu.fers.repository.TimeslotRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.sql.Timestamp
import java.util.*
import kotlin.streams.toList


@Service
class FreelancerService(
    private val freelancerRepository: FreelancerRepository,
    private val reservationRepository: ReservationRepository,
    private val timeslotRepository: TimeslotRepository,
    private val freelancerScoreRepository: FreelancerScoreRepository,
) {
    fun createFreelancer(createCustomerRequest: CreateFreelancerRequest): Freelancer {
        val existsAllByEmail = freelancerRepository.existsAllByEmail(createCustomerRequest.email)
        if (existsAllByEmail) {
            throw CustomerAlreadyCreatedException()
        }

        return freelancerRepository.save(
            Freelancer(
                email = createCustomerRequest.email,
                firstName = createCustomerRequest.firstname,
                lastName = createCustomerRequest.lastname,
                password = createCustomerRequest.password,
                phoneNumber = createCustomerRequest.phoneNumber,
                organizationId = createCustomerRequest.organizationId
            )
        )
    }

    fun login(logInCustomerRequest: LogInFreelancerRequest): Freelancer {
        val freelancer =
            freelancerRepository.findByEmail(logInCustomerRequest.email) ?: throw FreelancerNotFoundException()
        if (!freelancer.password.equals(logInCustomerRequest.password)) {
            throw PasswordDoesNotMatchException()
        }

        return freelancer
    }

    fun deleteFreelancer(freelancerEmail: String) {
        freelancerRepository.deleteByEmail(freelancerEmail)
    }

    fun getAvailableTimeslots(freelancerId: UUID): MutableList<AvailableTimeslotResponse>? {
        val findAllWithStartDateAndEndDateAndFreelancerId =
            reservationRepository.findAllWithStartDateAndEndDateAndFreelancerId(
                addDaysToNow(1)!!,
                addDaysToNow(7)!!,
                freelancerId
            )

        val timeSlots = timeslotRepository.findAll()

        val availableTimeslotResponseList: MutableList<AvailableTimeslotResponse> = mutableListOf()
        for (i in 1..7) {
            val date = addDaysToNow(i)!!
            val availableTimeslotResponse = AvailableTimeslotResponse(
                date = date.toString(),
                availableSlotList = getTimeslots(findAllWithStartDateAndEndDateAndFreelancerId, timeSlots, date)
            )
            availableTimeslotResponseList.add(availableTimeslotResponse)
        }

        return availableTimeslotResponseList
    }

    private fun getTimeslots(
        reservations: List<Reservation?>?,
        timeslots: List<Timeslot>,
        date: Timestamp
    ): List<GetTimeslotResponse> {
        val reservationList =
            reservations!!.stream().filter { reservation -> isSameDay(date, reservation!!.reservationDate!!) }.toList()
        return timeslots.stream().filter { timeslot ->
            !reservationList.stream().anyMatch { reservation -> reservation!!.timeslotId == timeslot.timeslotId }
        }.map {
            GetTimeslotResponse(
                timeslotId = it.timeslotId,
                startTime = it.startTime,
                endTime = it.endTime
            )
        }.toList()
    }

    private fun isSameDay(timestampOne: Timestamp, timestampTwo: Timestamp): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = timestampOne
        cal2.time = timestampTwo
        return cal1[Calendar.DAY_OF_YEAR] == cal2[Calendar.DAY_OF_YEAR] &&
                cal1[Calendar.YEAR] == cal2[Calendar.YEAR]
    }

    fun addDaysToNow(days: Int): Timestamp? {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, days)
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return Timestamp(calendar.timeInMillis)
    }

    fun getFreelancerScore(freelancerId: UUID): Double? {
        val findAllByFreelancerId = freelancerScoreRepository.findAllByFreelancerId(freelancerId)
        if (findAllByFreelancerId.isEmpty()) return 0.0
        return findAllByFreelancerId.sumOf { it.score!! } / findAllByFreelancerId.size
    }

    fun scoreFreelancer(scoreFreelancerRequest: ScoreFreelancerRequest) {
        if (freelancerScoreRepository.existsById(scoreFreelancerRequest.reservationId)) {
            throw RuntimeException("Freelancer score already given for this reservation")
        }
        val reservation = reservationRepository.findById(scoreFreelancerRequest.reservationId)
        if (reservation.isEmpty) {
            throw RuntimeException("Reservation cannot be found")
        }

        freelancerScoreRepository.save(
            FreelancerScore(
                reservationId = scoreFreelancerRequest.reservationId,
                customerId = reservation.get().customerId,
                freelancerId = reservation.get().freelancerId,
                score = scoreFreelancerRequest.score
            )
        )
    }
}