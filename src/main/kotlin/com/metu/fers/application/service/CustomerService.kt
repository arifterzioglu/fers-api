package com.metu.fers.application.service

import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.exception.CustomerAlreadyCreatedException
import com.metu.fers.domain.exception.CustomerNotFoundException
import com.metu.fers.domain.model.request.customer.CreateCustomerRequest
import com.metu.fers.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) {

    fun createCustomer(createCustomerRequest: CreateCustomerRequest): Customer {
        val existsAllByEmail = customerRepository.existsAllByEmail(createCustomerRequest.email)
        if (existsAllByEmail) {
            throw CustomerAlreadyCreatedException()
        }

        return customerRepository.save(
            Customer(
                email = createCustomerRequest.email,
                firstName = createCustomerRequest.firstname,
                lastName = createCustomerRequest.lastname,
                password = createCustomerRequest.password,
                phoneNumber = createCustomerRequest.phoneNumber,
                address = createCustomerRequest.address
            )
        )
    }

    fun updateCustomer(createCustomerRequest: CreateCustomerRequest): Customer {
        val customer = customerRepository.findByEmail(createCustomerRequest.email) ?: throw CustomerNotFoundException()
        customer.address = createCustomerRequest.address
        customer.firstName = createCustomerRequest.firstname
        customer.lastName = createCustomerRequest.lastname
        customer.password = createCustomerRequest.password

        customerRepository.save(customer)

        return customer
    }
}