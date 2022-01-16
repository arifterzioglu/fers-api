package com.metu.fers.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Freelancer")
open class Freelancer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "freelancer_id")
    open var freelancerId: UUID? = null,

    @Column(name = "email")
    open var email: String? = null,

    @Column(name = "firstname")
    open var firstName: String? = null,

    @Column(name = "lastname")
    open var lastName: String? = null,

    @Column(name = "password")
    open var password: String? = null,

    @Column(name = "phone_number")
    open var phoneNumber: String? = null,

    @Column(name = "organization_id")
    open var organizationId: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "organization_id",
        referencedColumnName = "organization_id",
        insertable = false,
        updatable = false,
        nullable = true,
    )
    open var freelancer: Organization? = null,
)