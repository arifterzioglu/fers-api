package com.metu.fers.domain.model.response.reservation

import java.sql.Timestamp
import java.util.*

data class GetReservationResponse(
    var reservationId: UUID? = null,
    var reservationDate: Timestamp? = null,
    var createdDate: Long? = null,
    var reservationStatus: String? = null,
    var timeslot: GetReservationTimeslot? = null,
    var customer: GetReservationCustomer? = null,
    var freelancer: GetReservationFreelancer? = null,
    var marketplaceFreelancerService: GetReservationMarketplaceFreelancerService? = null,
)

data class GetReservationTimeslot(
    var startTime: String? = null,
    var endTime: String? = null,
)

data class GetReservationCustomer(
    var customerId: UUID? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null,
    var address: String? = null
)

data class GetReservationFreelancer(
    var freelancerId: UUID? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null,
)

data class GetReservationMarketplaceFreelancerService(
    var serviceId: Int? = null,
    var serviceName: String? = null,
    var description: String? = null,
)