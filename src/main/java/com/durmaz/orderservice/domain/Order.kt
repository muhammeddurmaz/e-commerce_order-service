package com.durmaz.orderservice.domain

import com.durmaz.orderservice.domain.enums.OrderStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(name = "customer_name")
        var customerName: String,

        @Column(name = "total_quantity")
        var totalQuantity: Int,

        @Column(name = "total_price")
        var totalPrice: Double,

        @Enumerated(EnumType.STRING)
        var status: OrderStatus,

        @Column(name = "placed_date")
        var placedDate: LocalDateTime,

        var address: String,
)
