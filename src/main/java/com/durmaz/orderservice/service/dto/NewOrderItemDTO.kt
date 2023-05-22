package com.durmaz.orderservice.service.dto


import com.durmaz.orderservice.domain.OrderItem

data class NewOrderItemDTO @JvmOverloads constructor(
        val quantity: Int,
        val totalPrice: Double,
        val productId: Long,
){
    companion object{
        @JvmStatic
        fun toDto(orderItem: OrderItem) : NewOrderItemDTO{
            return NewOrderItemDTO(
                    orderItem.quantity,
                    orderItem.totalPrice,
                    orderItem.productId,
            )
        }
    }
}
