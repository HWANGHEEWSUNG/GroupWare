package com.example.groupware.connectDB

import com.android.volley.toolbox.StringRequest
import com.android.volley.Response
import com.example.groupware.loginScreen.ManagerInfo
import com.example.groupware.serverURL

class ManagerRegistRequest(
    managerInfo : ManagerInfo,
    picture1: String,
    picture2: String,
    picture3: String,
    picture4: String,
    picture5: String,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    private val parameters: Map<String, String> = mapOf(
        "userID" to managerInfo.email,
        "password" to managerInfo.password,
        "level" to "2",
        "name" to managerInfo.name,
        "address" to managerInfo.address,
        "telno" to managerInfo.phone,
        "address" to managerInfo.address,
        "registration" to managerInfo.registration,
        "type" to managerInfo.type,
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
        private const val URL = serverURL + "query/managerregist.php"
    }
}