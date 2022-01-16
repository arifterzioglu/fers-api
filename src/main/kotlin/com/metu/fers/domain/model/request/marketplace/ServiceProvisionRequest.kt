package com.metu.fers.domain.model.request.marketplace

import java.util.*

class ServiceProvisionRequest(
    val serviceId: Int? = null,
    val freelancerId: UUID? = null,
    val freelancerDescription: String? = null,
    val price: Float? = null
)