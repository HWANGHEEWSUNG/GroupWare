package com.example.groupware.connectDB

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley

@Composable
fun DbConnect() {
    Column {
        var userID by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var level by remember { mutableStateOf("") }
        val requestQueue: RequestQueue = Volley.newRequestQueue(LocalContext.current)

        TextField(
            value = userID,
            onValueChange = { userID = it },
            label = { Text("User Name") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("User password") }
        )
        TextField(
            value = level,
            onValueChange = { level = it },
            label = { Text("level") }
        )
        Button(onClick = {
            val responseListener = Response.Listener<String> { response ->
                // Handle response here
                println("Response: $response")
            }
            val errorListener = Response.ErrorListener { error ->
                // Handle error here
                println("Error: ${error.message}")
            }
            val registerRequest = SignUpRequest("userID", "password", 1, "","","",responseListener, errorListener)
            requestQueue.add(registerRequest)
        }) {
            Text("Register")
        }
    }
}
