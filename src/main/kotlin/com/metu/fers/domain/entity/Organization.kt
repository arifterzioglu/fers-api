package com.metu.fers.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Organization")
open class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "organization_id")
    open var organizationId: UUID? = null,

    @Column(name = "organization_name", unique = true)
    open var organizationName: String? = null,

    @Column(name = "tax_number")
    open var taxNumber: String? = null,

    @Column(name = "creation_date")
    open var creationDate: Long? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freelancer")
    open var freelancerList: MutableList<Freelancer?>? = null,
)