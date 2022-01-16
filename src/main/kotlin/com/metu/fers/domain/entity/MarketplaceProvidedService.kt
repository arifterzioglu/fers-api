package com.metu.fers.domain.entity

import java.util.*
import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "Provided_Service")
@IdClass(ProvidedServiceKey::class)
open class MarketplaceProvidedService(
    @Column(name = "service_id")
    @Id
    open var serviceId: Int? = null,

    @Column(name = "freelancer_id")
    @Id
    open var freelancerId: UUID? = null,

    @Column(name = "freelancer_description")
    open var freelancerDescription: String? = null,

    @Column(name = "price")
    open var price: Float? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "freelancer_id",
        referencedColumnName = "freelancer_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    open var freelancer: Freelancer? = null,
)

class ProvidedServiceKey(
    val freelancerId: UUID = UUID.randomUUID(),
    val serviceId: Int = 0
) : Serializable