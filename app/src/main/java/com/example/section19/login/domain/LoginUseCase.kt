package com.example.section19.login.domain

import com.example.section19.login.data.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(user: String, pass: String): Boolean {
        return repository.doLogin(user, pass)
    }
}