package com.example.groupware.connectDB

import com.android.volley.toolbox.StringRequest
import com.android.volley.Response
import com.example.groupware.loginScreen.UserInfo
import com.example.groupware.serverURL

class SignUpRequest(
    userInfo: UserInfo,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    private val parameters: Map<String, String> = mapOf(
        "userID" to userInfo.email,
        "password" to userInfo.password,
        "level" to userInfo.level,
        "name" to userInfo.name,
        "phone" to userInfo.phone,
        "birth" to userInfo.birth
    )

    override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = serverURL + "query/signup.php"
    }
}