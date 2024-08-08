package com.example.groupware.connectDB

import com.android.volley.toolbox.StringRequest
import com.android.volley.Response

class CenterRegistRequest(
    email: String,
    password: String,
    level: Int,
    name: String,
    address: String,
    telNo: String,
    registration: String,
    type: String,
    picture1: String,
    picture2: String,
    picture3: String,
    picture4: String,
    picture5: String,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    private val parameters: Map<String, String> = mapOf(
        "userID" to email,
        "password" to password,
        "level" to level.toString(),
        "name" to name,
        "address" to address,
        "telno" to telNo,
        "address" to address,
        "registration" to registration,
        "type" to type,
        "picture1" to picture1,
        "picture2" to picture2,
        "picture3" to picture3,
        "picture4" to picture4,
        "picture5" to picture5,
    )

    override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = "http://192.168.45.237/centerregist.php"
    }
}