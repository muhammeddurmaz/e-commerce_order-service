package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.OrderItem
import com.durmaz.orderservice.domain.enums.OrderItemStatus

data class OrderItemDTO @JvmOverloads constructor(
        var id:Long ? = null,
        var quantity:Int,
        var totalPrice:Double,
        var status: OrderItemStatus,
        var productId:Long,
        var orderDTO: OrderDTO
){
    companion object{
        @JvmStatic
        fun toDto(orderItem: OrderItem) : OrderItemDTO{
            return OrderItemDTO(
                    orderItem.id,
                    orderItem.quantity,
                    orderItem.totalPrice,
                    orderItem.status,
                    orderItem.productId,
                    OrderDTO.toDto(orderItem.order)
            )
        }

        @JvmStatic
        fun toEntity(orderItemDTO: OrderItemDTO): OrderItem{
            return OrderItem(
                    orderItemDTO.id,
                    orderItemDTO.quantity,
                    orderItemDTO.totalPrice,
                    orderItemDTO.status,
                    orderItemDTO.productId,
                    OrderDTO.toEntity(orderItemDTO.orderDTO)
            )
        }
    }
}
