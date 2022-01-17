package com.metu.fers.application.controller

import com.metu.fers.application.service.CustomerService
import com.metu.fers.application.service.FreelancerService
import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.entity.Freelancer
import com.metu.fers.domain.model.request.customer.LogInCustomerRequest
import com.metu.fers.domain.model.request.freelancer.CreateFreelancerRequest
import com.metu.fers.domain.model.request.freelancer.LogInFreelancerRequest
import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/freelancer")
class FreelancerController(private val freelancerService: FreelancerService) {


    @PostMapping("/create-freelancer")
    @ResponseStatus(HttpStatus.OK)
    fun createFreelancer(@RequestBody(required = true) createFreelancerRequest: CreateFreelancerRequest): ResponseEntity<Freelancer> {
        return ResponseEntity.ok(freelancerService.createFreelancer(createFreelancerRequest))
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody(required = true) logInCustomerRequest: LogInFreelancerRequest): ResponseEntity<Freelancer> {
        return ResponseEntity.ok(freelancerService.login(logInCustomerRequest))
    }

    //TODO: freelancer statistics gibi score'u freelancer'ın altına koy

    //TODO: freelancerın available time slotlarını dönen endpoint
}