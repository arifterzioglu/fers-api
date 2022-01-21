package com.metu.fers.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Reservation_Update_Outbox")
open class ReservationUpdateOutbox(

    @Column(name = "reservation_edit_request_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var reservationEditRequestId: UUID? = null,

    @Column(name = "reservation_id")
    open var reservationId: UUID? = null,

    @Column(name = "customer_id")
    open var customerId: UUID? = null,

    @Column(name = "freelancer_id")
    open var freelancerId: UUID? = null,

    @Column(name = "requested_reservation_date")
    open var requestedReservationDate: Timestamp? = null,

    @Column(name = "requested_slot_id")
    open var requestedSlotId: Int? = null,

    @Column(name = "request_owner")
    open var requestOwnerType: String? = null,

    @Column(name = "approval_status")
    open var approvalStatus: String? = null,

    @Column(name = "request_creation_date")
    open var requestCreatedDate: Timestamp? = null,

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "requested_slot_id",
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
)