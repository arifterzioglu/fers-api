package com.metu.fers.domain.entity

import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Organization")
open class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "organization_id")
    open var customerId: UUID? = null,

    @Column(name = "organization_name")
    open var organizationName: String? = null,

    @Column(name="tax_number")
    open var taxNumber: String? = null,

    @Column(name="creation_date")
    open var timestamp: Timestamp? = null,
)