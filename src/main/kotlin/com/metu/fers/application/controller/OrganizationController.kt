package com.metu.fers.application.controller

import com.metu.fers.application.service.OrganizationService
import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/organization")
class OrganizationController(private val organizationService: OrganizationService) {

    //TODO: add freelancer
    @PutMapping("/add-freelancer")
    @ResponseStatus(HttpStatus.OK)
    fun updateFreelancerOrganization(
        @RequestParam(required = true) freelancerId: UUID,
        @RequestParam(required = true) organizationId: UUID
    ): ResponseEntity<Any?> {
        organizationService.updateFreelancerOrganization(freelancerId, organizationId)
        return ResponseEntity.ok().build()
    }

    //TODO: Get organizations


    //TODO: Get organizations freelancers
}