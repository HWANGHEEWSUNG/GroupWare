package com.example.groupware.entryScreen

import androidx.compose.runtime.*
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EntryScreen(navController: NavController) {
    val backgroundColor = Color(0xFF6B75F4) // 이미지와 일치하도록 색상 설정
    val textColor = Color.White // 대비를 위한 흰색 텍스트

    // 2초 후에 자동으로 다른 화면으로 이동하도록 LaunchedEffect 사용
    LaunchedEffect(Unit) {
        // 2초 대기
        delay(2000)
        // MainScreen으로 이동
        navController.navigate("MainScreen") {
            // EntryScreen을 백스택에서 제거하여 뒤로 가기 시 이전 화면으로 가지 않도록 설정
            popUpTo("EntryScreen") { inclusive = true }
        }
    }

    // 메인 컨테이너로 파란색 배경을 설정
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center // 콘텐츠를 중앙 정렬
    ) {
        // 텍스트/로고를 위한 박스 설정
        Box(
            modifier = Modifier
                .align(Alignment.Center) // 하단 중앙 정렬
                .padding(bottom = 100.dp) // 필요한 경우 하단 패딩 추가
        ) {
            // 커스텀 로고 텍스트
            Text(
                text = "다짐", // 표시할 텍스트
                color = textColor,
                style = TextStyle(
                    fontSize = 80.sp, // 폰트 크기
                    fontWeight = FontWeight.Bold, // 폰트 두께
                    letterSpacing = 2.sp // 글자 간격
                )
            )
        }
    }
}