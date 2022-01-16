package com.metu.fers.domain.model.response.marketplace

data class GetServicesResponse(
    val id: Int,
    val serviceName: String,
    val serviceDescription: String,
)