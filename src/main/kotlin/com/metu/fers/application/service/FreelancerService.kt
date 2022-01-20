package com.metu.fers.application.service

import com.metu.fers.domain.entity.Freelancer
import com.metu.fers.domain.exception.CustomerAlreadyCreatedException
import com.metu.fers.domain.exception.FreelancerNotFoundException
import com.metu.fers.domain.exception.PasswordDoesNotMatchException
import com.metu.fers.domain.model.request.freelancer.CreateFreelancerRequest
import com.metu.fers.domain.model.request.freelancer.LogInFreelancerRequest
import com.metu.fers.domain.model.response.timeslot.AvailableTimeslotResponse
import com.metu.fers.repository.FreelancerRepository
import com.metu.fers.repository.ReservationRepository
import com.metu.fers.repository.TimeslotRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*


@Service
class FreelancerService(
    private val freelancerRepository: FreelancerRepository,
    private val reservationRepository: ReservationRepository,
    private val timeslotRepository: TimeslotRepository,
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

    fun getAvailableTimeslots(freelancerId: UUID): List<AvailableTimeslotResponse>? {
        val timeSlots = timeslotRepository.findAll()

        reservationRepository.findAllWithStartDateAndEndDateAndFreelancerId(
            addDaysToNow(1)!!,
            addDaysToNow(7)!!,
            freelancerId
        )

        return emptyList()
    }

    fun isSameDay(timestampOne: Timestamp, timestampTwo: Timestamp): Boolean {
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
}