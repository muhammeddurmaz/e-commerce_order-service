package com.durmaz.orderservice.domain

import com.durmaz.orderservice.domain.enums.OrderItemStatus
import javax.persistence.*



@Entity
@Table(name ="order_items")
data class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,


        @Column(name ="quantity")
        val quantity: Int,

        @Column(name = "total_price")
        val totalPrice:Double,

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        val status:OrderItemStatus,

        @Column(name ="product_id", nullable = false)
        val productId: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id", nullable = false)
        val order: Order,
)
