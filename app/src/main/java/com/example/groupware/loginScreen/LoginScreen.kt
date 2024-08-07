package com.example.groupware.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberId by remember { mutableStateOf(false) }
    var stayLoggedIn by remember { mutableStateOf(false) }

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
            value = username,
            onValueChange = { username = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0))
                        .padding(16.dp)
                ) {
                    if (username.isEmpty()) {
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
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
            onClick = { navController.navigate("mainScreen")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "로그인")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "아이디 찾기", color = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "비밀번호 찾기", color = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {navController.navigate("registerScreen")},
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