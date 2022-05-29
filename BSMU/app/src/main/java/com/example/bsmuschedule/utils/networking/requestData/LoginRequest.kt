package com.example.bsmuschedule.utils.networking.requestData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(var email: String, var password: String): Parcelable
