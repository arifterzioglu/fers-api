package com.metu.fers.application.service

import com.metu.fers.domain.entity.MarketplaceFreelancerService
import com.metu.fers.domain.entity.MarketplaceProvidedService
import com.metu.fers.domain.exception.ServiceAlreadyProvidedByFreelancer
import com.metu.fers.domain.model.request.marketplace.NewMarketplaceServiceCreationRequest
import com.metu.fers.domain.model.request.marketplace.ServiceProvisionRequest
import com.metu.fers.domain.model.response.marketplace.GetServicesResponse
import com.metu.fers.repository.ProvidedServiceRepository
import com.metu.fers.repository.ServiceRepository
import org.springframework.stereotype.Service

@Service
class MarketplaceService(
    private val serviceRepository: ServiceRepository,
    private val providedServiceRepository: ProvidedServiceRepository
) {
    fun addNewService(newMarketplaceServiceCreationRequest: NewMarketplaceServiceCreationRequest) {
        serviceRepository.save(
            MarketplaceFreelancerService(
                serviceName = newMarketplaceServiceCreationRequest.serviceName,
                description = newMarketplaceServiceCreationRequest.description
            )
        )
    }

    fun getServices(): List<GetServicesResponse>? {
        return serviceRepository.findAll().map {
            GetServicesResponse(
                id = it.serviceId!!,
                serviceName = it.serviceName!!,
                serviceDescription = it.description!!
            )
        }
    }

    fun removeService(serviceName: String) {
        serviceRepository.deleteAllByServiceName(serviceName)
    }

    fun handleServiceProvisionRequest(serviceProvisionRequest: ServiceProvisionRequest): MarketplaceProvidedService {
        val existsByServiceIdAndFreelancerId = providedServiceRepository.existsByServiceIdAndFreelancerId(
            serviceProvisionRequest.serviceId,
            serviceProvisionRequest.freelancerId
        )

        if (existsByServiceIdAndFreelancerId) {
            throw ServiceAlreadyProvidedByFreelancer()
        }

        return providedServiceRepository.save(
            MarketplaceProvidedService(
                serviceId = serviceProvisionRequest.serviceId,
                freelancerDescription = serviceProvisionRequest.freelancerDescription,
                freelancerId = serviceProvisionRequest.freelancerId,
                price = serviceProvisionRequest.price
            )
        )
    }
}