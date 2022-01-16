package com.metu.fers.domain.model.response.marketplace

import java.util.*

data class GetServiceProviderResponse(
    val freelancerId: UUID,
    val firstname: String,
    val lastname: String,
    val organizationId: UUID?,
    val freelancerDescription: String,
    val price: Float,
)