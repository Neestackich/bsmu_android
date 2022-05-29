package com.example.bsmuschedule.utils.networking.responseData

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()
