package com.metu.fers.domain.model.request.freelancer

import java.util.*

class CreateFreelancerRequest(
    val email: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val organizationId: UUID? = null,
)