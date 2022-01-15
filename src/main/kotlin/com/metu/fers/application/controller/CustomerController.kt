package com.metu.fers.application.controller

import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import com.metu.fers.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(private val customerRepository: CustomerRepository) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    fun createCustomer(): ResponseEntity<Customer> {
        val customer = Customer(
            email = "arif@gmail.com",
            firstName = "Arif",
            lastName = "Terzioğlu",
            password = "asdsa",
            phoneNumber = "5443455566",
            address = "çayyolu"
        )

        customerRepository.save(customer)
        return ResponseEntity.ok(customer)
    }
}