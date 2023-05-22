package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.enums.OrderItemStatus

data class OrderItemDTO(
        val quantity: Int,
        val totalPrice: Double,
        val productId: Long,
        val status: OrderItemStatus,
        var orderID:Long?
)
