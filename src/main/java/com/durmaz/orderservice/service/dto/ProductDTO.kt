package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.enums.OrderItemStatus

data class ProductDTO(
        val id:Long,
        val name:String,
        val descriptipn:String,
        val price:Double,
)
