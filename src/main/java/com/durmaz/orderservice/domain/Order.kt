package com.durmaz.orderservice.domain

import com.durmaz.orderservice.domain.enums.OrderStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @Column(name = "customer_name")
        val customerName: String,

        @Column(name = "total_quantity")
        val totalQuantity: Int,

        @Column(name = "total_price")
        val totalPrice: Double,

        @Enumerated(EnumType.STRING)
        @Column(name="order_status")
        val status: OrderStatus,

        @Column(name = "placed_date")
        val placedDate: LocalDateTime,

        @Column(name= "adress")
        val address: String,
)
