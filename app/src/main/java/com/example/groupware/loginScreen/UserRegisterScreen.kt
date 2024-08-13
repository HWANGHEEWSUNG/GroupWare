package com.example.groupware.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.groupware.connectDB.SignUpRequest


class UserInfo(
    var success: String = "",
    val email: String = "",
    val password: String = "",
    val level: Int = 0, //
    var name: String = "",
    var phone: String = "",
    var birthDate: String = "" // birthYear + birthMonth + birthDay 결합된 값
)


@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }
    var birthMonth by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,  // 상단 정렬
        horizontalAlignment = Alignment.CenterHorizontally  // 가로 중앙 정렬
    ) {
        Text(
            text = "회원가입",
            style = MaterialTheme.typography.titleLarge, // Material 3의 적절한 타이포그래피 스타일 사용
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "아이디(이메일)") },
            placeholder = { Text(text = "이메일 형식의 아이디를 입력해 주세요.") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "비밀번호") },
            placeholder = { Text(text = "영문, 숫자 포함 6~20자로 설정해 주세요.") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "비밀번호 확인") },
            placeholder = { Text(text = "비밀번호를 다시 한번 입력해 주세요.") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "이름") },
            placeholder = { Text(text = "이름을 입력해 주세요.") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text(text = "연락처") },
            placeholder = { Text(text = "-를 제외하고 숫자만 입력해 주세요.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            OutlinedTextField(
                value = birthYear,
                onValueChange = { birthYear = it },
                label = { Text(text = "생년") },
                placeholder = { Text(text = "년") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )
            OutlinedTextField(
                value = birthMonth,
                onValueChange = { birthMonth = it },
                label = { Text(text = "월") },
                placeholder = { Text(text = "월") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )
            OutlinedTextField(
                value = birthDay,
                onValueChange = { birthDay = it },
                label = { Text(text = "일") },
                placeholder = { Text(text = "일") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = { termsAccepted = it }
            )
            Text(text = "이용약관 및 개인정보취급방침에 동의합니다.", color = Color.Gray)
        }

        val requestQueue: RequestQueue = Volley.newRequestQueue(LocalContext.current) //필수~~

        Button(
            onClick = {
                val responseListener = Response.Listener<String> { response ->
                    // Handle response here
                    println("Response: $response")
                }
                val errorListener = Response.ErrorListener { error ->
                    // Handle error here
                    println("Error: ${error.message}")
                }

                val userInfo = UserInfo("0", email, password, 1, name, phone, birthYear + birthMonth + birthDay)
                val registerRequest = SignUpRequest(
                    userInfo,
                    responseListener,
                    errorListener
                )
                requestQueue.add(registerRequest)
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "다음")
        }
    }
}
