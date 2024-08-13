package com.example.groupware.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // 스크롤 가능하도록 추가
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "시설 정보 수정 페이지",
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )

        // Profile Picture and Name
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with the actual profile image
            contentDescription = "Profile Picture",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Fityou피트니스 역삼 1호점",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 운동 종목
        Text(
            text = "운동 시설",
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
            var isChecked4 by remember { mutableStateOf(true) }

            Checkbox(
                checked = isChecked1,
                onCheckedChange = { isChecked1 = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF42A1F5)) // 커스텀 파란색 설정
            )
            Text(text = "헬스장")
            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked2,
                onCheckedChange = { isChecked2 = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF42A1F5)) // 커스텀 파란색 설정
            )
            Text(text = "필라테스")
            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked3,
                onCheckedChange = { isChecked3 = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF42A1F5)) // 커스텀 파란색 설정
            )
            Text(text = "요가")

            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked4,
                onCheckedChange = { isChecked4 = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF42A1F5)) // 커스텀 파란색 설정
            )
            Text(text = "골프")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            var isChecked5 by remember { mutableStateOf(true) }
            var isChecked6 by remember { mutableStateOf(false) }
            var isChecked7 by remember { mutableStateOf(true) }

            Checkbox(
                checked = isChecked5,
                onCheckedChange = { isChecked5 = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF42A1F5)) // 커스텀 파란색 설정
            )
            Text(text = "크로스핏")

            Checkbox(
                checked = isChecked6,
                onCheckedChange = { isChecked6 = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF42A1F5)) // 커스텀 파란색 설정
            )
            Text(text = "실내 클라이밍")

            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked7,
                onCheckedChange = { isChecked7 = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF42A1F5)) // 커스텀 파란색 설정
            )
            Text(text = "테니스")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 시설 사진 업로드
        Text(
            text = "시설 사진 업로드",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with the actual profile image
                contentDescription = "Profile Picture",
                modifier = Modifier.size(120.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with the actual profile image
                contentDescription = "Profile Picture",
                modifier = Modifier.size(130.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with the actual profile image
                contentDescription = "Profile Picture",
                modifier = Modifier.size(130.dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with the actual profile image
                contentDescription = "Profile Picture",
                modifier = Modifier.size(80.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with the actual profile image
                contentDescription = "Profile Picture",
                modifier = Modifier.size(80.dp)
            )
        }


        // 1일권 포인트 가격
        Text(
            text = "1일권 포인트 가격",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 40.dp) // 가로 100.dp, 세로 40.dp 크기 설정
                .background(Color.LightGray) // 회색 배경 설정
                .align(Alignment.Start) // 화면 왼쪽에 배치
        ) {

            // Box 내부 글씨
            Text(
                text = "100 Point",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center),

                )
        }
        Spacer(modifier = Modifier.height(12.dp))


        // 운영 시간
        Text(
            text = "운영 시간",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .size(width = 130.dp, height = 40.dp) // 가로 100.dp, 세로 40.dp 크기 설정
                .background(Color.LightGray) // 회색 배경 설정
                .align(Alignment.Start) // 화면 왼쪽에 배치
        ) {

            // 운영 시간 Box 내부 글씨
            Text(
                text = "6:00am~23:00pm",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center),

                )
        }
        Spacer(modifier = Modifier.height(8.dp))

        //영업장 주소 글씨
        Text(
            text = "영업장 주소",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 40.dp) // 가로 100.dp, 세로 40.dp 크기 설정
                .background(Color.LightGray) // 회색 배경 설정
                .align(Alignment.Start) // 화면 왼쪽에 배치
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Change Button
        Button(
            onClick = { /* Handle Password Change */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF42A1F5)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally) // 버튼을 중앙에 배치
        ) {
            Text(text = "비밀번호 변경")
        }

        Spacer(modifier = Modifier.height(24.dp)) // 버튼 아래 여백 추가
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGymProfileScreen() {
    GymProfileScreen(navController = rememberNavController())
}
