package com.metu.fers.application.controller

import com.metu.fers.application.service.OrganizationService
import com.metu.fers.domain.entity.Freelancer
import com.metu.fers.domain.entity.Organization
import com.metu.fers.domain.model.request.organization.AddOrganizationRequest
import com.metu.fers.domain.model.response.reservation.CreateReservationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/organization")
class OrganizationController(private val organizationService: OrganizationService) {

    @PutMapping("/update-freelancer-organization")
    @ResponseStatus(HttpStatus.OK)
    fun updateFreelancerOrganization(
        @RequestParam(required = true) freelancerId: UUID,
        @RequestParam(required = true) organizationId: UUID
    ): ResponseEntity<Any?> {
        organizationService.updateFreelancerOrganization(freelancerId, organizationId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/add-organization")
    @ResponseStatus(HttpStatus.OK)
    fun addOrganization(@RequestBody(required = true) addOrganizationRequest: AddOrganizationRequest): ResponseEntity<Any?> {
        organizationService.addOrganization(addOrganizationRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/organizations")
    @ResponseStatus(HttpStatus.OK)
    fun getOrganizations(): ResponseEntity<MutableList<Organization>?> {
        return ResponseEntity.ok(organizationService.getOrganizations())
    }

    @GetMapping("/organization-freelancers")
    @ResponseStatus(HttpStatus.OK)
    fun getOrganizationFreelancers(@RequestParam organizationId: UUID): ResponseEntity<MutableList<Freelancer?>?> {
        return ResponseEntity.ok(organizationService.getOrganizationFreelancers(organizationId))
    }
}