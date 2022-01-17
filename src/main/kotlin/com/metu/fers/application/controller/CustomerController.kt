package com.metu.fers.application.controller

import com.metu.fers.application.service.CustomerService
import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.model.request.customer.CreateCustomerRequest
import com.metu.fers.domain.model.request.customer.LogInCustomerRequest
import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import com.metu.fers.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController(private val userService: CustomerService) {

    @PostMapping("/create-customer")
    @ResponseStatus(HttpStatus.OK)
    fun createCustomer(@RequestBody(required = true) createCustomerRequest: CreateCustomerRequest): ResponseEntity<Customer> {
        return ResponseEntity.ok(userService.createCustomer(createCustomerRequest))
    }

    @PutMapping("/edit-customer")
    @ResponseStatus(HttpStatus.OK)
    fun updateCustomer(@RequestBody(required = true) createCustomerRequest: CreateCustomerRequest): ResponseEntity<Customer> {
        return ResponseEntity.ok(userService.updateCustomer(createCustomerRequest))
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody(required = true) logInCustomerRequest: LogInCustomerRequest): ResponseEntity<Customer> {
        return ResponseEntity.ok(userService.login(logInCustomerRequest))
    }

    //freelancer statistics gibi score'u customer'ın altına koy
}