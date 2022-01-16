package com.metu.fers.domain.entity

import javax.persistence.*

@Entity
@Table(name = "Service")
open class MarketplaceFreelancerService(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    open var serviceId: Int? = null,

    @Column(name = "service_name")
    open var serviceName: String? = null,

    @Column(name = "service_description")
    open var description: String? = null,
)