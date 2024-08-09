package com.example.groupware.entryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.groupware.R // R 파일을 명시적으로 가져옴

@Composable
fun TheVeryFirstScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(90.dp))

        // 일반 사용자 버튼
        Button(
            onClick = { navController.navigate("loginScreen") },
            shape = RoundedCornerShape(24.dp), // More rounded corners
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A90E2)),
            modifier = Modifier
                .width(220.dp)
                .height(60.dp)
                .padding(bottom = 16.dp)
                .shadow(8.dp, shape = RoundedCornerShape(24.dp)) // Add shadow
        ) {
            Text(text = "일반 사용자", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 관리자 버튼
        Button(
            onClick = { navController.navigate("manreScreen") },
            shape = RoundedCornerShape(24.dp), // More rounded corners
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A90E2)),
            modifier = Modifier
                .width(220.dp)
                .height(60.dp)
                .padding(bottom = 16.dp)
                .shadow(8.dp, shape = RoundedCornerShape(24.dp)) // Add shadow
        ) {
            Text(text = "관리자", color = Color.White, fontSize = 16.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTheVeryFirstScreen() {
    TheVeryFirstScreen(navController = rememberNavController())
}
