package com.example.groupware.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun UserProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with actual profile image resource
                contentDescription = "profile",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "username",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Ensure text is clearly visible
                )
                Text(
                    text = "email",
                    fontSize = 16.sp,
                    color = Color.DarkGray // A bit darker gray for better readability
                )
            }
        }

        // Points and Coupons Section
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "포인트",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "0P",
                    fontSize = 22.sp, // Slightly larger for emphasis
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Darker for emphasis
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "쿠폰",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "0개",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "찜",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "0개",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.CardMembership, // Membership icon
                    contentDescription = "회원권",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF4A90E2) // Subtle blue for the icons
                )
                Text(
                    text = "회원권",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Receipt, // Payment history icon
                    contentDescription = "결제내역",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF4A90E2)
                )
                Text(
                    text = "결제내역",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Chat, // Chat icon
                    contentDescription = "상담톡",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF4A90E2)
                )
                Text(
                    text = "상담톡",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Star, // Review or feedback icon
                    contentDescription = "후기관리",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF4A90E2)
                )
                Text(
                    text = "후기관리",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Additional Options
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle, // Facility request icon
                    contentDescription = "시설등록요청",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "시설등록요청",
                    color = Color.Black,
                    fontSize = 16.sp // Larger text for main options
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd, // Invite friend icon
                    contentDescription = "친구초대",
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "친구초대", color = Color.Red)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.HelpOutline, // Inquiry or question icon
                    contentDescription = "1:1 문의",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "1:1 문의",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info, // FAQ icon
                    contentDescription = "FAQ",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "FAQ",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp, // Logout icon
                    contentDescription = "로그아웃",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "로그아웃",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}


        @Preview(showBackground = true)
        @Composable
        fun PreviewUserProfileScreen() {
            UserProfileScreen(navController = rememberNavController())
        }




