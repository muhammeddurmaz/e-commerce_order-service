package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.enums.OrderItemStatus

data class ProductDTO(
        val id: Long?,
        val name: String,
        val description: String,
        val price: Double,
        val categoryId: Long,
        val availableStock: Int,
)
