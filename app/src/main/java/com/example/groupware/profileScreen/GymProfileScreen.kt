package com.example.groupware.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.groupware.R

@Composable
fun GymProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Picture and Name
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with the actual profile image
            contentDescription = "Profile Picture",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "홍길동",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Member Information Section
        Text(
            text = "회원 정보",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Group Selection Checkboxes
        Row(verticalAlignment = Alignment.CenterVertically) {
            var isChecked1 by remember { mutableStateOf(true) }
            var isChecked2 by remember { mutableStateOf(false) }
            var isChecked3 by remember { mutableStateOf(true) }

            Checkbox(
                checked = isChecked1,
                onCheckedChange = { isChecked1 = it }
            )
            Text(text = "신규회원")
            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked2,
                onCheckedChange = { isChecked2 = it }
            )
            Text(text = "아임웹기초강의")
            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked3,
                onCheckedChange = { isChecked3 = it }
            )
            Text(text = "도매그룹")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Member Level Radio Buttons
        Text(
            text = "쇼핑등급",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        var selectedLevel by remember { mutableStateOf("기본등급") }

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedLevel == "기본등급",
                    onClick = { selectedLevel = "기본등급" }
                )
                Text(text = "기본등급")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedLevel == "실버등급",
                    onClick = { selectedLevel = "실버등급" }
                )
                Text(text = "실버등급")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedLevel == "골드등급",
                    onClick = { selectedLevel = "골드등급" }
                )
                Text(text = "골드등급")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                var autoChangeChecked by remember { mutableStateOf(false) }
                Checkbox(
                    checked = autoChangeChecked,
                    onCheckedChange = { autoChangeChecked = it }
                )
                Text(text = "자동 등급 변경 사용 안함")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nickname and Email
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "닉네임", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "홍길동", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "계정", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "plipop@naver.com", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Change Button
        Button(onClick = { /* Handle Password Change */ }) {
            Text(text = "비밀번호 변경")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Marketing Preferences
        Column(modifier = Modifier.fillMaxWidth()) {
            var isSmsChecked by remember { mutableStateOf(false) }
            var isEmailChecked by remember { mutableStateOf(false) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isSmsChecked,
                    onCheckedChange = { isSmsChecked = it }
                )
                Text(text = "SMS 수신 동의")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isEmailChecked,
                    onCheckedChange = { isEmailChecked = it }
                )
                Text(text = "E-Mail 수신 동의")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGymProfileScreen() {
    GymProfileScreen(navController = rememberNavController())
}

