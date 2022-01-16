package com.metu.fers.repository

import com.metu.fers.domain.entity.MarketplaceProvidedService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface ProvidedServiceRepository : JpaRepository<MarketplaceProvidedService, Int> {
    fun existsByServiceIdAndFreelancerId(serviceId: Int?, freelancerId: UUID?): Boolean

    @Transactional
    fun deleteByServiceIdAndFreelancerId(serviceId: Int?, freelancerId: UUID?): Unit

    fun findAllByServiceId(serviceId: Int?): List<MarketplaceProvidedService>
}