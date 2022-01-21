package com.metu.fers.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Freelancer_Score")
open class FreelancerScore(
    @Id
    @Column(name = "reservation_id")
    open var reservationId: UUID? = null,

    @Column(name = "freelancer_id")
    open var freelancerId: UUID? = null,

    @Column(name = "customer_id")
    open var customerId: UUID? = null,

    @Column(name = "score")
    open var score: Double? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "reservation_id",
        referencedColumnName = "reservation_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    @JsonIgnore
    open var reservation: Reservation? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "customer_id",
        referencedColumnName = "customer_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    @JsonIgnore
    open var customer: Customer? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "freelancer_id",
        referencedColumnName = "freelancer_id",
        insertable = false,
        updatable = false,
        nullable = false,
    )
    @JsonIgnore
    open var freelancer: Freelancer? = null,
)