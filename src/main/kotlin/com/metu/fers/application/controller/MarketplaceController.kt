package com.metu.fers.application.controller

import com.metu.fers.application.service.MarketplaceService
import com.metu.fers.domain.entity.MarketplaceProvidedService
import com.metu.fers.domain.model.request.marketplace.NewMarketplaceServiceCreationRequest
import com.metu.fers.domain.model.request.marketplace.ProvidedServiceDeletionRequest
import com.metu.fers.domain.model.request.marketplace.ServiceProvisionRequest
import com.metu.fers.domain.model.response.marketplace.GetServiceProviderResponse
import com.metu.fers.domain.model.response.marketplace.GetServicesResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/marketplace")
class MarketplaceController(private val marketplaceService: MarketplaceService) {

    @PostMapping("/add-service")
    @ResponseStatus(HttpStatus.OK)
    fun addService(
        @RequestBody(required = true)
        newMarketplaceServiceCreationRequest: NewMarketplaceServiceCreationRequest
    ): ResponseEntity<Any?> {
        marketplaceService.addNewService(newMarketplaceServiceCreationRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/services")
    @ResponseStatus(HttpStatus.OK)
    fun getServices(): ResponseEntity<List<GetServicesResponse>> {
        return ResponseEntity.ok(marketplaceService.getServices())
    }

    @DeleteMapping("/delete-service")
    @ResponseStatus(HttpStatus.OK)
    fun deleteServices(@RequestParam(required = true) serviceName: String): ResponseEntity<Any?> {
        marketplaceService.removeService(serviceName)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/add-provided-service")
    @ResponseStatus(HttpStatus.OK)
    fun addProvidedService(@RequestBody(required = true) serviceProvisionRequest: ServiceProvisionRequest): ResponseEntity<MarketplaceProvidedService?> {
        return ResponseEntity.ok(marketplaceService.handleServiceProvisionRequest(serviceProvisionRequest))
    }

    @DeleteMapping("/delete-provided-service")
    @ResponseStatus(HttpStatus.OK)
    fun deleteProvidedService(@RequestBody(required = true) providedServiceDeletionRequest: ProvidedServiceDeletionRequest): ResponseEntity<Any?> {
        marketplaceService.removeProvidedService(providedServiceDeletionRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/service-providers")
    @ResponseStatus(HttpStatus.OK)
    fun getServiceProviders(@RequestParam(required = true) serviceId: Int): ResponseEntity<List<GetServiceProviderResponse>>  {
        return ResponseEntity.ok(marketplaceService.getServiceProvidersByServiceId(serviceId))
    }
}