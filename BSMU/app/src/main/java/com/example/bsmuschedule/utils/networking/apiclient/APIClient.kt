package com.example.bsmuschedule.utils.networking.apiclient

import androidx.lifecycle.LiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.bsmuschedule.utils.networking.apiclient.endpoint.Endpoint
import com.example.bsmuschedule.utils.networking.requestData.LoginRequest
import com.example.bsmuschedule.utils.networking.responseData.ApiResponse
import com.example.bsmuschedule.utils.networking.responseData.login.LoginResponse
import com.example.bsmuschedule.utils.networking.apiclient.adapter.LiveDataCallAdapterFactory

class APIClient: APIClientType {

    private class Constants {
        companion object {
            const val baseBackend = "http://10.0.2.2:8000/"
        }
    }

    private val endpoint: Endpoint by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseBackend)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Endpoint::class.java)
    }

    override fun loginRequest(loginRequestData: LoginRequest): LiveData<ApiResponse<LoginResponse>> {
        return endpoint.login(loginRequestData.email, loginRequestData.password)
    }

}