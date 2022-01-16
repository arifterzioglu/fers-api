package com.metu.fers.domain.model.request.customer

class CreateCustomerRequest(
    val email: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
)