package com.metu.fers.repository

import com.metu.fers.domain.entity.MarketplaceFreelancerService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface ServiceRepository : JpaRepository<MarketplaceFreelancerService, Int> {
    @Transactional
    fun deleteAllByServiceName(serviceName: String?)
}