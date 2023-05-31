package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.enums.OrderStatus
import java.time.LocalDateTime

data class ViewOrderDetailDTO @JvmOverloads constructor(
        val id:Long,
        val customerName:String,
        val totalQuantity:Int,
        val totalPrice:Double,
        val status:OrderStatus,
        val placedDate:LocalDateTime,
        val adress:String,
        var orderItemDetails: List<ViewOrderItemDTO>? = ArrayList()
)
