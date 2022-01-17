package com.metu.fers.repository

import com.metu.fers.domain.entity.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrganizationRepository : JpaRepository<Organization, UUID> {
    fun existsByOrganizationName(organizationName: String?): Boolean
}