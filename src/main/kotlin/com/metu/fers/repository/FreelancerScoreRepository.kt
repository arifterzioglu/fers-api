package com.metu.fers.repository

import com.metu.fers.domain.entity.FreelancerScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FreelancerScoreRepository : JpaRepository<FreelancerScore, UUID> {
    fun findAllByFreelancerId(freelancerId: UUID): List<FreelancerScore>
}