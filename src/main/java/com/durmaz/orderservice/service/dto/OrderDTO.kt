package com.durmaz.orderservice.service.dto

import com.durmaz.orderservice.domain.Order
import com.durmaz.orderservice.domain.enums.OrderStatus
import java.time.LocalDateTime

data class OrderDTO @JvmOverloads constructor(
        val id:Long ? = null,
        val customerName: String,
        val totalQuantitiy: Int,
        val totalPrice: Double,
        val status: OrderStatus,
        val placedDate: LocalDateTime,
        val address: String
){
    companion object{
        @JvmStatic
        fun toDto(order: Order) : OrderDTO{
            return OrderDTO(
                    order.id,
                    order.customerName,
                    order.totalQuantity,
                    order.totalPrice,
                    order.status,
                    order.placedDate,
                    order.address
            )
        }

        @JvmStatic
        fun toEntity(orderDTO: OrderDTO): Order{
            return Order(
                    orderDTO.id,
                    orderDTO.customerName,
                    orderDTO.totalQuantitiy,
                    orderDTO.totalPrice,
                    orderDTO.status,
                    orderDTO.placedDate,
                    orderDTO.address
            )
        }
    }
}

