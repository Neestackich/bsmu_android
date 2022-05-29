package com.example.bsmuschedule.utils.networking.responseData

data class ApiErrorResponse<T>(val errorCode: Int, val errorMessage: String) : ApiResponse<T>()
