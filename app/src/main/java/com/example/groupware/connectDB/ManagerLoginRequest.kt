package com.example.groupware.connectDB

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.groupware.loginScreen.CenterItem
import com.example.groupware.serverURL

class ManagerLoginRequest(
    managerInfo: CenterItem,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    private val parameters: Map<String, String> = mapOf(
        "userID" to managerInfo.email,
        "password" to managerInfo.password,
    )

    override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = serverURL + "query/login.php"
    }
}