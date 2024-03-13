package com.example.section19.login.data.network

import android.util.Log
import com.example.section19.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient
) {



    suspend fun doLogin(user: String, pass: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = loginClient.doLogin()
            Log.d("JZM", "LOGIN SERVICE: $response")
            response.body()?.success?: false
        }
    }
}