package com.metu.fers.application.service

import com.metu.fers.domain.entity.Freelancer
import com.metu.fers.domain.entity.Organization
import com.metu.fers.domain.exception.FreelancerNotFoundException
import com.metu.fers.domain.exception.OrganizationAlreadyCreatedException
import com.metu.fers.domain.exception.OrganizationNotFoundException
import com.metu.fers.domain.model.request.organization.AddOrganizationRequest
import com.metu.fers.repository.FreelancerRepository
import com.metu.fers.repository.OrganizationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrganizationService(
    private val freelancerRepository: FreelancerRepository,
    private val organizationRepository: OrganizationRepository
) {
    fun updateFreelancerOrganization(freelancerId: UUID, organizationId: UUID) {
        val freelancer = freelancerRepository.findById(freelancerId)
        freelancer.ifPresentOrElse({ obj ->
            obj.organizationId = organizationId
            freelancerRepository.save(freelancer.get())
        }
        ) { throw FreelancerNotFoundException() }
    }

    fun addOrganization(addOrganizationRequest: AddOrganizationRequest) {
        val existByOrganizationName =
            organizationRepository.existsByOrganizationName(addOrganizationRequest.organizationName)

        if (existByOrganizationName) {
            throw OrganizationAlreadyCreatedException()
        }

        organizationRepository.save(
            Organization(
                organizationName = addOrganizationRequest.organizationName,
                taxNumber = addOrganizationRequest.taxNumber,
                creationDate = System.currentTimeMillis(),
            )
        )
    }

    fun getOrganizations(): MutableList<Organization> {
        return organizationRepository.findAll()
    }

    fun getOrganizationFreelancers(organizationId: UUID): MutableList<Freelancer?>? {
        var freelancerList: MutableList<Freelancer?>? = null
        val organizationOptional = organizationRepository.findById(organizationId)
        organizationOptional.ifPresentOrElse({ obj ->
            freelancerList = obj.freelancerList
        }
        ) { throw OrganizationNotFoundException() }
        return freelancerList
    }
}