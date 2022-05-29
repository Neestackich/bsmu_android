package com.example.bsmuschedule.utils.networking.apiclient.endpoint

import androidx.lifecycle.LiveData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

import com.example.bsmuschedule.utils.networking.responseData.ApiResponse
import com.example.bsmuschedule.utils.networking.responseData.login.LoginResponse

interface Endpoint {
    @FormUrlEncoded
    @POST("authentication/login/")
    fun login(@Field("email") email: String, @Field("password") password: String): LiveData<ApiResponse<LoginResponse>>
}
