package com.metu.fers.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Reservation")
open class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    open var reservationId: UUID? = null,

    @Column(name = "freelancer_id")
    open var freelancerId: UUID? = null,

    @Column(name = "customer_id")
    open var customerId: UUID? = null,

    @Column(name = "service_id")
    open var serviceId: Int? = null,

    @Column(name = "reservation_date")
    open var reservationDate: Timestamp? = null,

    @Column(name = "timeslot_id")
    open var timeslotId: Int? = null,

    @Column(name = "created_date")
    open var createdDate: Long? = null,

    @Column(name = "updated_date")
    open var updatedDate: Long? = null,

    @Column(name = "reservation_status")
    open var reservationStatus: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "timeslot_id",
        referencedColumnName = "timeslot_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    @JsonIgnore
    open var timeslot: Timeslot? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "customer_id",
        referencedColumnName = "customer_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    @JsonIgnore
    open var customer: Customer? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "freelancer_id",
        referencedColumnName = "freelancer_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    @JsonIgnore
    open var freelancer: Freelancer? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "service_id",
        referencedColumnName = "service_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    @JsonIgnore
    open var marketplaceFreelancerService: MarketplaceFreelancerService? = null,
)