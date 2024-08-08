package com.example.groupware.connectDB

import com.android.volley.toolbox.StringRequest
import com.android.volley.Response

class SignUpRequest(
    email: String,
    password: String,
    level: Int,
    name: String,
    phone: String,
    birth: String,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    private val parameters: Map<String, String> = mapOf(
        "userID" to email,
        "password" to password,
        "level" to level.toString(),
        "name" to name,
        "phone" to phone,
        "birth" to birth
    )

    override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = "http://192.168.45.237/signup.php"
    }
}