package com.example.groupware.gymScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.groupware.R
import java.net.URL

data class Centerinfo(
    val userID: String,
    val name: String,
    val address: String,
    val phone: String,
    val point: Int,
    val registration: String,
    val type: String,
    val picture1: URL,
    val picture2: URL,
    val picture3: URL,
    val picture4: URL,
    val picture5: URL,
)

@Composable
fun GymScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B0A40))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                GymCard()
            }
            // Add more items if needed
        }
    }
}

@Composable
fun GymCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.gym), // Replace with actual image resource
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "시청 피트니스온",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "5.0 ",
                    color = Color(0xFFFFD700),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "(13) 서울 중구 무교동 · 0.1km",
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "예약필요",
                color = Color(0xFF6A1B9A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "8% 37,910원~/월",
                color = Color(0xFFE53935),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "무료 프로그램: OT 2회 무료",
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "무료 서비스: 사우나, 바디워시, 샴푸 제공",
                color = Color.Gray
            )
        }
    }
}