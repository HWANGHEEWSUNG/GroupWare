package com.example.groupware.gymScreen


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Loyalty
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.groupware.R
import com.example.groupware.connectDB.CenterListRequest
import com.example.groupware.loginScreen.CenterItem
import kotlinx.serialization.json.Json


@Composable
fun GymScreen(navController: NavController) {
    var showFilterDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var centerList = remember { mutableStateListOf<CenterItem>()}
    val errorListener = Response.ErrorListener { error ->
        // Handle error here
        println("Error: ${error.message}")
    }

    val centerListResponseListener =
        Response.Listener<String> { responseCenters ->
            val decodedList = Json.decodeFromString<List<CenterItem>>(responseCenters)
            centerList.clear() // 기존 리스트를 비운 후
            centerList.addAll(decodedList) // 새 리스트 항목 추가
        }

    val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    requestQueue.add(
        CenterListRequest(
            centerListResponseListener,
            errorListener
        )
    )

    Scaffold(
        topBar = {
            CustomTopAppBar(onFilterClick = { showFilterDialog = true })
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    centerList.forEach{ centerItem ->
                        items(1) {
                            GymCard(navController = navController, centerItem)
                        }
                    }
                }
            }

            if (showFilterDialog) {
                FilterDialog(onDismiss = { showFilterDialog = false })
            }
        }
    )
}

@Composable
fun CustomTopAppBar(onFilterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "서울 중구 태평로 1가",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { onFilterClick() }) {
                Icon(
                    imageVector = Icons.Filled.FilterAlt,
                    contentDescription = "Filter",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Filter, Day Pass, and Coupon Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomIconButton(
                icon = Icons.Filled.FilterAlt,
                text = "필터"
            ) {
                onFilterClick()
            }

            CustomIconButton(
                icon = Icons.Filled.CalendarToday,
                text = "일일권"
            ) {
                // Day pass action
            }

            CustomIconButton(
                icon = Icons.Filled.Loyalty,
                text = "선착순쿠폰"
            ) {
                // Coupon action
            }
        }
    }
}

@Composable
fun CustomIconButton(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF0F0F0))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun GymCard(navController: NavController, centerItem: CenterItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("gyminfoScreen")
            },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            centerItem.picture1?.let{
                Image(bitmap = decodeBase64ToBitmap(it), contentDescription = "")
            }
//            Image(
//                painter = painterResource(id = R.drawable.gym), // Replace with actual image resource
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp),
//                contentScale = ContentScale.Crop
//            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = centerItem.name,
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
                    text = centerItem.address,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = centerItem.comment ?:"",
                color = Color(0xFF6A1B9A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${centerItem.point}원/월",
                color = Color(0xFFE53935),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "예약필요",
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

fun decodeBase64ToBitmap(base64String: String): ImageBitmap {
    // Base64 문자열을 디코딩하여 바이트 배열로 변환
    val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)

    // 바이트 배열을 Bitmap으로 변환
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size).asImageBitmap()
}