package com.metu.fers.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Customer")
open class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    open var customerId: UUID? = null,

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

    @Column(name = "address")
    open var address: String? = null
)