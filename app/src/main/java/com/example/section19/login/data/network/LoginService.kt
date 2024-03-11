package com.example.section19.login.data.network

import android.util.Log
import com.example.section19.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {

    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(user: String, pass: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(LoginClient::class.java).doLogin()
            Log.d("JZM", "LOGIN SERVICE: $response")
            response.body()?.success?: false
        }
    }
}