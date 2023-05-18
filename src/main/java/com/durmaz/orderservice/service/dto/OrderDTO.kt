package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.enums.OrderStatus
import java.time.LocalDateTime

data class OrderDTO @JvmOverloads constructor(
        val id:Long?,
        val customerName: String,
        val totalQuantitiy: Int,
        val totalPrice: Double,
        val status: OrderStatus,
        val placedDate: LocalDateTime,
        val address: String
)
