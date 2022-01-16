package com.metu.fers.application.service

import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.entity.Freelancer
import com.metu.fers.domain.model.request.customer.CreateCustomerRequest
import com.metu.fers.repository.CustomerRepository
import com.metu.fers.repository.FreelancerRepository
import com.metu.fers.domain.exception.CustomerAlreadyCreatedException
import com.metu.fers.domain.exception.CustomerNotFoundException
import com.metu.fers.domain.model.request.freelancer.CreateFreelancerRequest
import org.springframework.stereotype.Service

@Service
class FreelancerService(
    private val freelancerRepository: FreelancerRepository
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
}