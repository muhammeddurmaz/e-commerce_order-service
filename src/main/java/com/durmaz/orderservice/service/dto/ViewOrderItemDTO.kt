package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.enums.OrderItemStatus

data class ViewOrderItemDTO(
        val id:Long,
        val quantity:Int,
        val totalPrice: Double,
        val status: OrderItemStatus,
        val productDTO: ProductDTO
)
