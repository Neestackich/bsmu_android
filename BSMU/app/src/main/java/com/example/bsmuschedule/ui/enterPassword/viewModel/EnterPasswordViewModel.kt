package com.example.bsmuschedule.ui.enterPassword.viewModel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.example.bsmuschedule.base.BaseViewModel
import com.example.bsmuschedule.utils.networking.apiclient.APIClientType
import com.example.bsmuschedule.utils.networking.requestData.LoginRequest
import com.example.bsmuschedule.utils.networking.responseData.ApiErrorResponse
import com.example.bsmuschedule.utils.networking.responseData.ApiSuccessResponse
import com.example.bsmuschedule.utils.observer.EmptyContent
import com.example.bsmuschedule.utils.observer.SingleEvent
import com.example.bsmuschedule.utils.validators.StringValidator.isPasswordValid

@HiltViewModel
class EnterPasswordViewModel @Inject constructor(private val apiClient: APIClientType) : BaseViewModel() {

    private lateinit var loginRequestData: LoginRequest

    private val isButtonEnabledMutable = MutableLiveData<Boolean>(false)
    val isButtonEnabled: LiveData<Boolean> get() = isButtonEnabledMutable

    private val returnToEnterEmailScreenMutable = MutableLiveData<SingleEvent<EmptyContent>>()
    val returnToEnterEmailScreenEvent: LiveData<SingleEvent<EmptyContent>> get() = returnToEnterEmailScreenMutable

    fun bindIntentData(loginRequestData: LoginRequest) {
        this.loginRequestData = loginRequestData
    }

    fun handleTextChange(textString: Editable?) {
        val passwordString = textString.toString().trim()
        validatePassword(passwordString)
    }

    fun handleLoginButtonClick(textString: Editable?) {
        val password = textString.toString().trim()
        loginRequestData.password = password
        apiClient.loginRequest(loginRequestData).observeForever(Observer { response ->
            if (response is ApiSuccessResponse) {
                val test = true
            }

            else if (response is ApiErrorResponse) {
                val test = false
            }
        })
    }

    fun backButtonClick() {
        returnToEnterEmailScreenMutable.value = SingleEvent(EmptyContent())
    }

    private fun validatePassword(password: String) {
        isButtonEnabledMutable.value = (password.count() > 0 && isPasswordValid(password))
    }

}