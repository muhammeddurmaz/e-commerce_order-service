package com.durmaz.orderservice.domain

import com.durmaz.orderservice.domain.enums.OrderItemStatus
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull


@Entity
@Table(name = "order_items")
data class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @Min(1)
        @Column(name ="quantity")
        val quantity: Int,

        @Column(name = "total_price")
        val totalPrice:Double,

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        val status:OrderItemStatus,

        @NotNull
        @Column(name ="product_id", nullable = false)
        val productId: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order", nullable = false)
        val order: Order,
)
