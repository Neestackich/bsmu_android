package com.example.bsmuschedule.ui.enterEmail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged

import com.example.bsmuschedule.base.BaseActivity
import com.example.bsmuschedule.databinding.EnterEmailBinding
import com.example.bsmuschedule.ui.enterEmail.viewModel.EnterLoginViewModel
import com.example.bsmuschedule.ui.enterPassword.view.EnterPasswordActivity
import com.example.bsmuschedule.utils.networking.requestData.LoginRequest
import com.example.bsmuschedule.utils.observer.SingleEvent
import com.example.bsmuschedule.utils.observer.observe
import com.example.bsmuschedule.utils.observer.observeEvent

class EnterEmailActivity : BaseActivity() {

    private val viewModel: EnterLoginViewModel by viewModels()
    private lateinit var binding: EnterEmailBinding

    private class Constants {
        companion object {
            const val loginRequestDataKey = "loginRequestDataKey"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
    }

    override fun initViewBinding() {
        binding = EnterEmailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun bindViewModel() {
        observe(viewModel.isButtonEnabled, ::handleEmailValidation)
        observeEvent(viewModel.openEnterPasswordScreenEvent, ::openEnterPasswordScreen)
    }

    private fun setup() {
        setupListeners()
    }

    private fun setupListeners() {
        binding.emailTextField.doAfterTextChanged {
            viewModel.handleTextChange(it)
        }
        binding.nextButton.setOnClickListener {
            hideKeyboard()
            nextButtonClick()
        }
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val view = binding.root
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun nextButtonClick() {
        viewModel.handleNextButtonClick(binding.emailTextField.text)
    }

    private fun handleEmailValidation(isValid: Boolean) {
        binding.nextButton.isEnabled = isValid
        binding.nextButton.isClickable = isValid
    }

    private fun openEnterPasswordScreen(navigationEvent: SingleEvent<LoginRequest>) {
        navigationEvent.getContentIfNotHandled()?.let {
            val enterPasswordScreenIntent = Intent(this, EnterPasswordActivity::class.java).apply {
                putExtra(Constants.loginRequestDataKey, it)
            }
            startActivity(enterPasswordScreenIntent)
        }
    }

}