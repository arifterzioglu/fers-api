package com.metu.fers.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Admin")
open class Admin(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    open var adminId: UUID? = null,

    @Column(name = "email", unique = true)
    open var email: String? = null,

    @Column(name = "firstname")
    open var firstName: String? = null,

    @Column(name = "lastname")
    open var lastName: String? = null,

    @Column(name = "password")
    @JsonIgnore
    open var password: String? = null,

    @Column(name = "phone_number", unique = true)
    open var phoneNumber: String? = null,
)