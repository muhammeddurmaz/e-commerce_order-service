package com.durmaz.orderservice.service.dto

data class CreateOrderRequestDTO(
        val customerName: String,
        val totalQuantity: Int,
        val totalAmount: Double,
        val adress: String,
        val orderItems: List<NewOrderItemDTO>
)
