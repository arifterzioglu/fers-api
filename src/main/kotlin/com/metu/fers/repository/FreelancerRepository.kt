package com.metu.fers.repository

import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.entity.Freelancer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FreelancerRepository : JpaRepository<Freelancer, UUID> {
    fun existsAllByEmail(
        email: String?,
    ): Boolean

    fun findByEmail(email: String?): Freelancer?

    fun deleteByEmail(freelancerEmail: String)
}