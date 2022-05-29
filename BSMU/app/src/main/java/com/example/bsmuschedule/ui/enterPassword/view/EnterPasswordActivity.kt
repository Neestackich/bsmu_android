package com.example.bsmuschedule.ui.enterPassword.view

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint

import com.example.bsmuschedule.base.BaseActivity
import com.example.bsmuschedule.databinding.EnterPasswordBinding
import com.example.bsmuschedule.ui.enterPassword.viewModel.EnterPasswordViewModel
import com.example.bsmuschedule.utils.networking.requestData.LoginRequest
import com.example.bsmuschedule.utils.observer.EmptyContent
import com.example.bsmuschedule.utils.observer.SingleEvent
import com.example.bsmuschedule.utils.observer.observe
import com.example.bsmuschedule.utils.observer.observeEvent

@AndroidEntryPoint
class EnterPasswordActivity : BaseActivity() {

    private val viewModel: EnterPasswordViewModel by viewModels()
    private lateinit var binding: EnterPasswordBinding

    private class Constants {
        companion object {
            const val loginRequestDataKey = "loginRequestDataKey"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
    }

    override fun bindViewModel() {
        val intentData = intent.getParcelableExtra<LoginRequest>(Constants.loginRequestDataKey)

        if (intentData != null) {
            val unwrappedIntentData = intentData!!
            viewModel.bindIntentData(unwrappedIntentData)
        }

        observe(viewModel.isButtonEnabled, ::handlePasswordValidation)
        observeEvent(viewModel.returnToEnterEmailScreenEvent, ::handleBackToEnterEmailScreen)
    }

    override fun initViewBinding() {
        binding = EnterPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setup() {
        setupListeners()
    }

    private fun setupListeners() {
        binding.passwordTextField.doAfterTextChanged {
            viewModel.handleTextChange(it)
        }
        binding.loginButton.setOnClickListener {
            hideKeyboard()
            loginButtonClick()
        }
        binding.backButton.setOnClickListener {
            backButtonClick()
        }
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun loginButtonClick() {
        viewModel.handleLoginButtonClick(binding.passwordTextField.text)
    }

    private fun backButtonClick() {
        viewModel.backButtonClick()
    }

    private fun hideKeyboard() {
        val view = binding.root
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun handlePasswordValidation(isValid: Boolean) {
        binding.loginButton.isEnabled = isValid
        binding.loginButton.isClickable = isValid
    }

    private fun handleBackToEnterEmailScreen(navigationEvent: SingleEvent<EmptyContent>) {
        finish()
    }

}