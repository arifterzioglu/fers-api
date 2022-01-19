package com.metu.fers.application.controller

import com.metu.fers.application.service.CustomerService
import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.model.request.customer.CreateCustomerRequest
import com.metu.fers.domain.model.request.customer.LogInCustomerRequest
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

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCustomer(@RequestParam(required = true) customerEmail: String): ResponseEntity<Any?> {
        return ResponseEntity.ok(userService.deleteCustomer(customerEmail))
    }

    //freelancer statistics gibi score'u customer'ın altına koy
}