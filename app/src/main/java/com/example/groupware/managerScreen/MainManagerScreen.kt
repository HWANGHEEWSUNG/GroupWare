package com.example.groupware.managerScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import com.example.groupware.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))
        AttendanceCard()
        Spacer(modifier = Modifier.height(16.dp))
        IconRow()
        Spacer(modifier = Modifier.height(16.dp))
        AttendanceList()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.notification_bell),
                contentDescription = "Notification Icon",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Fityou피트니스 1호점", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Image(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                contentDescription = "Arrow Down Icon",
                modifier = Modifier.size(24.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(25.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun AttendanceCard() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFEEF1FF),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "출석확인을 진행해 주세요.", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* 출석 확인하기 클릭 처리 */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7285FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "출석확인하기", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "캘린더", fontSize = 14.sp, color = Color.Gray)
                Text(text = "10.17  20:00 ~ 20:50", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun IconRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconWithText(R.drawable.profile, "회원", 24.dp)
        IconWithText(R.drawable.calendar, "일정", 24.dp)
        IconWithText(R.drawable.profit_growth, "매출", 24.dp)
        IconWithText(R.drawable.people, "구성원", 24.dp)
        IconWithText(R.drawable.setting, "설정", 24.dp)
    }
}

@Composable
fun IconWithText(iconId: Int, text: String, iconSize: Dp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = CircleShape,
            color = Color.White,
            modifier = Modifier.size(48.dp),
            shadowElevation = 4.dp
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.size(iconSize).padding(6.dp) // 아이콘 가장자리 padding
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, fontSize = 14.sp)
    }
}

@Composable
fun AttendanceList() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "실시간 출석", fontSize = 15.sp, fontWeight = FontWeight.Bold)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "더보기", fontSize = 14.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            AttendanceItem("김재국", "[헬스+골프]", "1분 전", R.drawable.logo)
            Spacer(modifier = Modifier.height(8.dp))
            AttendanceItem("김현우", "[헬스+골프]", "5분 전", R.drawable.logo)
            Spacer(modifier = Modifier.height(8.dp))
            AttendanceItem("성다희", "[헬스+필라테스+요가]", "19분 전", R.drawable.logo)
            Spacer(modifier = Modifier.height(8.dp))
            AttendanceItem("황희성", "[헬스+실내 클라이밍]", "19분 전", R.drawable.logo)
        }
    }
}

@Composable
fun AttendanceItem(name: String, activity: String, time: String, profileImageId: Int, attendanceStatus: String = "") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = profileImageId),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = activity, fontSize = 12.sp, color = Color.Gray)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = time, fontSize = 12.sp, color = Color.Gray)
        if (attendanceStatus.isNotEmpty()) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = attendanceStatus, fontSize = 12.sp, color = Color(0xFFFFA726), fontWeight = FontWeight.Bold)
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}
