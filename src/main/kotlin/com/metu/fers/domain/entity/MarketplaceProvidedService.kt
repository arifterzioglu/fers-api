package com.metu.fers.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Provided_Service")
open class MarketplaceProvidedService(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    open var serviceId: Int? = null,

    @Column(name = "freelancer_id")
    open var freelancerId: UUID? = null,

    @Column(name = "freelancer_description")
    open var freelancerDescription: String? = null,

    @Column(name = "price")
    open var price: Float? = null,
)