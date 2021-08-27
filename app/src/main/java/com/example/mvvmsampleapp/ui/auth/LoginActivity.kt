package com.example.mvvmsampleapp.ui.auth

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.utils.hide
import com.example.mvvmsampleapp.utils.show
import com.example.mvvmsampleapp.utils.toast
import kotlin.math.log

class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(AuthViewModel::class.java)

        binding?.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        binding.progressBar.show()
        toast("Login started")
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            binding.progressBar.hide()
            toast(it)
        })
    }

    override fun onError(errorMessage: String) {
        binding.progressBar.hide()
        toast(errorMessage)
    }
}