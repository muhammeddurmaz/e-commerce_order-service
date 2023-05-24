package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.enums.OrderStatus
import java.time.LocalDateTime

data class ViewOrderDetailDTO(
        val id:Long,
        val customerName:String,
        val totalQuantity:Int,
        val totalPrice:Double,
        val status:OrderStatus,
        val placedDate:LocalDateTime,
        val adress:String,
        val orderItemDetails: List<ViewOrderItemDTO>
)
