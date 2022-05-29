package com.example.bsmuschedule.ui.enterLogin.viewModel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.bsmuschedule.base.BaseViewModel
import com.example.bsmuschedule.utils.networking.requestData.LoginRequest
import com.example.bsmuschedule.utils.observer.SingleEvent
import com.example.bsmuschedule.utils.validators.StringValidator.isEmailValid

class EnterLoginViewModel : BaseViewModel() {

    private val isButtonEnabledMutable = MutableLiveData<Boolean>(false)
    val isButtonEnabled: LiveData<Boolean> get() = isButtonEnabledMutable

    private val openEnterPasswordScreenMutable = MutableLiveData<SingleEvent<LoginRequest>>()
    val openEnterPasswordScreenEvent: LiveData<SingleEvent<LoginRequest>> get() = openEnterPasswordScreenMutable

    fun handleTextChange(textString: Editable?) {
        val emailString = textString.toString().trim()
        validateEmail(emailString)
    }

    fun handleNextButtonClick(textString: Editable?) {
        val email = textString.toString().trim()
        val loginRequestData = LoginRequest(email, "")
        goToEnterPasswordScreen(loginRequestData)
    }

    private fun goToEnterPasswordScreen(loginRequestData: LoginRequest) {
        openEnterPasswordScreenMutable.value = SingleEvent(loginRequestData)
    }

    private fun validateEmail(email: String) {
        isButtonEnabledMutable.value = (email.count() > 0 && isEmailValid(email))
    }

}