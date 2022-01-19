package com.metu.fers.domain.model.request.admin

class CreateAdminRequest(
    val email: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
)