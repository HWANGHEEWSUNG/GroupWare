package com.example.groupware.loginScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.groupware.connectDB.CenterListRequest
import com.example.groupware.connectDB.UserLoginRequest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class CenterItem(
    var email: String = "",
    var name: String = "",
    var address: String = "",
    var telno: String = "",
    var registration: String = "",
    var type: String = "",
    var comment: String? = "",
    var point: String = "0",
    var picture1: String? = "",
    var picture2: String? = "",
    var picture3: String? = "",
    var picture4: String? = "",
    var picture5: String? = "",
)

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberId by remember { mutableStateOf(false) }
    var stayLoggedIn by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "로그인",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0))
                        .padding(16.dp)
                ) {
                    if (email.isEmpty()) {
                        Text(text = "아이디", color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        BasicTextField(
            value = password,
            onValueChange = { password = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0))
                        .padding(16.dp)
                ) {
                    if (password.isEmpty()) {
                        Text(text = "영문, 숫자 포함 6~20자의 비밀번호", color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Checkbox(
                checked = rememberId,
                onCheckedChange = { rememberId = it }
            )
            Text(text = "아이디 저장", color = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = stayLoggedIn,
                onCheckedChange = { stayLoggedIn = it }
            )
            Text(text = "로그인 상태 유지", color = Color.Gray)
        }

        Button(
            onClick = {

                val userInfo = UserInfo("0", email, password, "1", "", "", "")

                val errorListener = Response.ErrorListener { error ->
                    // Handle error here
                    println("Error: ${error.message}")
                }

                val loginResponseListener = Response.Listener<String> { responseUser ->
                    // Handle response here
                    println("Response: $responseUser")
                    val userData = Json.decodeFromString<UserInfo>(responseUser)

                    if (userData.success == "1") {
                        navController.navigate("gymScreen")
                    } else {
                        Toast.makeText(context, "실패 ", Toast.LENGTH_SHORT).show()
                    }
                }

                val registerRequest = UserLoginRequest(
                    userInfo,
                    loginResponseListener,
                    errorListener
                )
                val requestQueue: RequestQueue = Volley.newRequestQueue(context)
                requestQueue.add(registerRequest)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "로그인")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "아이디 찾기", color = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "비밀번호 찾기", color = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { navController.navigate("registerScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "회원가입")
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Serializable
data class UserData(val success: String, val name: String, val phone: String, val birth: String)