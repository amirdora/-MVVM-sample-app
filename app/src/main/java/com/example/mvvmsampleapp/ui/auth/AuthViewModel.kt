package com.example.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repositories.UserRepository
import com.example.mvvmsampleapp.utils.ApiException
import com.example.mvvmsampleapp.utils.Coroutines

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener : AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onError("Invalid email or password")
            return
        }
        Coroutines.main {
            try {
                val authResponse = UserRepository().userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onError(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onError(e.message!!)
            }

        }

    }
}