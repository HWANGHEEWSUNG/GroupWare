package com.example.groupware.connectDB

import com.android.volley.toolbox.StringRequest
import com.android.volley.Response
import com.example.groupware.loginScreen.UserInfo

class UserLoginRequest(
    userInfo: UserInfo,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    private val parameters: Map<String, String> = mapOf(
        "userID" to userInfo.email,
        "password" to userInfo.password,
    )

    override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = "http://192.168.45.50/query/login.php"
    }
}