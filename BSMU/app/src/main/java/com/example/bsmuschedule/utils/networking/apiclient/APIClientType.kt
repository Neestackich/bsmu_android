package com.example.bsmuschedule.utils.networking.apiclient

import androidx.lifecycle.LiveData

import com.example.bsmuschedule.utils.networking.requestData.LoginRequest
import com.example.bsmuschedule.utils.networking.responseData.ApiResponse
import com.example.bsmuschedule.utils.networking.responseData.login.LoginResponse

interface APIClientType {
    fun loginRequest(loginRequestData: LoginRequest): LiveData<ApiResponse<LoginResponse>>
}