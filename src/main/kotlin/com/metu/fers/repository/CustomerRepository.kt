package com.metu.fers.repository

import com.metu.fers.domain.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<Customer, UUID> {
    fun existsAllByEmail(
        email: String?,
    ): Boolean

    fun findByEmail(email: String?): Customer?
    fun deleteByEmail(customerEmail: String)
}