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
import com.example.mvvmsampleapp.data.db.entities.User
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.utils.hide
import com.example.mvvmsampleapp.utils.show
import com.example.mvvmsampleapp.utils.snackBar
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
    }

    override fun onSuccess(user: User) {
        binding.progressBar.hide()
        binding.rootLayout.snackBar("${user.name} is logged in")

    }

    override fun onError(errorMessage: String) {
        binding.progressBar.hide()
        binding.rootLayout.snackBar(errorMessage)
    }
}