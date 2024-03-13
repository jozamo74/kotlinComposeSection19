package com.example.section19.login.data

import com.example.section19.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: LoginService
) {

    suspend fun doLogin(user: String, pass: String): Boolean {
        return api.doLogin(user, pass)
    }
}