package com.example.groupware.connectDB

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.groupware.serverURL

class CenterListRequest(
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    companion object {
        private const val URL = serverURL + "query/centerlist.php"
    }
}