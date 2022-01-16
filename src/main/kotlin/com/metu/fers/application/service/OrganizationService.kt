package com.metu.fers.application.service

import com.metu.fers.domain.exception.FreelancerNotFoundException
import com.metu.fers.repository.FreelancerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrganizationService(
    private val freelancerRepository: FreelancerRepository
) {
    //TODO: add organization
    fun updateFreelancerOrganization(freelancerId: UUID, organizationId: UUID) {
        val freelancer = freelancerRepository.findById(freelancerId)
        if (freelancer.isEmpty) {
            throw FreelancerNotFoundException()
        }
        freelancer.get().organizationId = organizationId

        freelancerRepository.save(freelancer.get())
    }
}