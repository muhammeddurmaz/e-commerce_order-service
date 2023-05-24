package com.durmaz.orderservice.service.dto

data class ResponseDTO<T>(
        var message: String? = "",
        var success: Boolean? = false,
        var data : T
){
    fun message(message: String): ResponseDTO<T> {
        this.message = message
        return this
    }

    fun success(success: Boolean): ResponseDTO<T> {
        this.success = success
        return this
    }

    fun data(data: T): ResponseDTO<T> {
        this.data = data
        return this
    }

    fun build(): ResponseDTO<T> {
        return ResponseDTO(message, success, data)
    }
}
