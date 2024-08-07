package com.example.groupware.mainScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBar() },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(Color.White)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SearchBar()
                ShortcutButtons()
                ContentCards()
                AdditionalInfo()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "서울 중구 태평로 1가", fontSize = 16.sp)
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
    )
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        placeholder = { Text("어떤 운동을 찾고 계신가요?") },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun ShortcutButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Use Modifier.weight(1f) to evenly distribute space among ShortcutButtons
        ShortcutButton(icon = Icons.Default.Percent, label = "다짐회원가", modifier = Modifier.weight(1f))
        ShortcutButton(icon = Icons.Default.PersonAdd, label = "통합회원권", modifier = Modifier.weight(1f))
        ShortcutButton(icon = Icons.Default.Home, label = "일일권", modifier = Modifier.weight(1f)) // 적절한 아이콘으로 수정
    }
}

@Composable
fun ShortcutButton(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier // Modifier passed to allow weight
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Red,
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        )
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ContentCards() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Use Modifier.weight(1f) to distribute space among ContentCards
        ContentCard(title = "내 주변 운동시설", subtitle = "최저가 할인", modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.size(8.dp))
        ContentCard(title = "지도에서 찾기", modifier = Modifier.weight(1f))
    }
    Spacer(modifier = Modifier.size(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ContentCard(title = "P.T 가격 비교", modifier = Modifier.weight(1f))
    }
}

@Composable
fun ContentCard(title: String, subtitle: String? = null, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold)
        subtitle?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = Color.Blue)
        }
    }
}

@Composable
fun AdditionalInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "운동 고민이 있는 다짐 앱 유저라면?", fontWeight = FontWeight.Bold)
        Text(text = "다짐 고민상담소 오픈했어요")
    }
}