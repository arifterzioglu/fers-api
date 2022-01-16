package com.metu.fers.application.controller

import com.metu.fers.application.service.CustomerService
import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.model.request.customer.CreateCustomerRequest
import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import com.metu.fers.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController(private val userService: CustomerService){

    @PostMapping("/create-customer")
    @ResponseStatus(HttpStatus.OK)
    fun createCustomer(@RequestBody createCustomerRequest: CreateCustomerRequest): ResponseEntity<Customer> {
        return ResponseEntity.ok(userService.createCustomer(createCustomerRequest))
    }

    @PutMapping("/edit-customer")
    @ResponseStatus(HttpStatus.OK)
    fun updateCustomer(@RequestBody createCustomerRequest: CreateCustomerRequest): ResponseEntity<Customer> {
        return ResponseEntity.ok(userService.updateCustomer(createCustomerRequest))
    }
}