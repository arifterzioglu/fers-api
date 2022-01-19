package com.metu.fers.application.service

import com.metu.fers.domain.entity.Admin
import com.metu.fers.domain.entity.Customer
import com.metu.fers.domain.exception.AdminNotFoundException
import com.metu.fers.domain.exception.CustomerAlreadyCreatedException
import com.metu.fers.domain.exception.CustomerNotFoundException
import com.metu.fers.domain.exception.PasswordDoesNotMatchException
import com.metu.fers.domain.model.request.admin.CreateAdminRequest
import com.metu.fers.domain.model.request.admin.LogInAdminRequest
import com.metu.fers.domain.model.request.customer.CreateCustomerRequest
import com.metu.fers.domain.model.request.customer.LogInCustomerRequest
import com.metu.fers.domain.model.request.freelancer.LogInFreelancerRequest
import com.metu.fers.repository.AdminRepository
import com.metu.fers.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val adminRepository: AdminRepository,
) {
    fun createAdmin(createAdminRequest: CreateAdminRequest): Admin {
        val existsAllByEmail = adminRepository.existsAllByEmail(createAdminRequest.email)
        if (existsAllByEmail) {
            throw CustomerAlreadyCreatedException()
        }

        return adminRepository.save(
            Admin(
                email = createAdminRequest.email,
                firstName = createAdminRequest.firstname,
                lastName = createAdminRequest.lastname,
                password = createAdminRequest.password,
                phoneNumber = createAdminRequest.phoneNumber,
            )
        )
    }

    fun getAdmin(email: String): Admin? {
        val findByEmail = adminRepository.findByEmail(email)
        if (findByEmail.isEmpty) {
            throw AdminNotFoundException()
        }

        return findByEmail.get()
    }

    fun login(logInAdminRequest: LogInAdminRequest): Admin? {
        val admin = adminRepository.findByEmail(logInAdminRequest.email)
        if (admin.isEmpty) {
            throw AdminNotFoundException()
        }

        if (!admin.get().password.equals(logInAdminRequest.password)) {
            throw PasswordDoesNotMatchException()
        }

        return admin.get()
    }
}