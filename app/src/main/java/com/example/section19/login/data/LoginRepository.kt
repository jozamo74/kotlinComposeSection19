package com.example.section19.login.data

import com.example.section19.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()
    suspend fun doLogin(user: String, pass: String): Boolean {
        return api.doLogin(user, pass)
    }
}