package com.metu.fers.application.controller

import com.metu.fers.application.service.FreelancerService
import com.metu.fers.domain.entity.Freelancer
import com.metu.fers.domain.model.request.freelancer.CreateFreelancerRequest
import com.metu.fers.domain.model.request.freelancer.LogInFreelancerRequest
import com.metu.fers.domain.model.response.timeslot.AvailableTimeslotResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCustomer(@RequestParam(required = true) freelancerEmail: String): ResponseEntity<Any?> {
        return ResponseEntity.ok(freelancerService.deleteFreelancer(freelancerEmail))
    }

    @GetMapping("/available-timeslots")
    @ResponseStatus(HttpStatus.OK)
    fun getAvailableTimeslots(@RequestParam(required = true) freelancerId: UUID): ResponseEntity<MutableList<AvailableTimeslotResponse>> {
        return ResponseEntity.ok(freelancerService.getAvailableTimeslots(freelancerId))
    }

    //TODO: freelancer statistics gibi score'u freelancer'ın altına koy
}