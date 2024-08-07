package com.example.groupware.profileScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.groupware.R

@Composable
fun ProfileScreen(navController: NavController) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B0A40))
            .padding(16.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            UserInfo(selectedImageUri) {
                launcher.launch("image/*")
            }
            Spacer(modifier = Modifier.height(16.dp))
            UserStatus()
            Spacer(modifier = Modifier.height(16.dp))
            UserProducts()
            Spacer(modifier = Modifier.height(16.dp))
            Actions()
        }
    }
}

@Composable
fun UserInfo(selectedImageUri: Uri?, onImageClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("김다짐") }
    var phoneNumber by remember { mutableStateOf("010-1234-1234") }

    if (showDialog) {
        EditUserInfoDialog(
            currentName = name,
            currentPhoneNumber = phoneNumber,
            onDismiss = { showDialog = false },
            onSave = { newName, newPhoneNumber ->
                name = newName
                phoneNumber = newPhoneNumber
                showDialog = false
            }
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        if (selectedImageUri != null) {
            Image(
                painter = rememberImagePainter(selectedImageUri),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onImageClick)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onImageClick)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            Text(text = phoneNumber, color = Color.Gray)
            Button(
                onClick = { /* 아무런 기능이 없도록 설정 */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                ),
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(text = "계약서", color = Color.White)
            }
        }
        IconButton(onClick = { showDialog = true }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun EditUserInfoDialog(
    currentName: String,
    currentPhoneNumber: String,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var name by remember { mutableStateOf(currentName) }
    var phoneNumber by remember { mutableStateOf(currentPhoneNumber) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Edit User Info") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") }
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text(text = "Phone Number") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(name, phoneNumber) }) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}

@Composable
fun UserStatus() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StatusItem(label = "최근 방문", value = "오늘 방문", icon = Icons.Default.DateRange)
        Spacer(modifier = Modifier.height(8.dp))
        StatusItem(label = "누적방문횟수", value = "24회", icon = Icons.Default.CheckCircle)
        Spacer(modifier = Modifier.height(8.dp))
        StatusItem(label = "만료", value = "163일 남음", icon = Icons.Default.Timer)
        Spacer(modifier = Modifier.height(8.dp))
        StatusItem(label = "누적결제금액", value = "1,000,000원", icon = Icons.Default.AttachMoney)
    }
}

@Composable
fun StatusItem(label: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$label: ",
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = value,
            color = Color.Black
        )
    }
}

@Composable
fun UserProducts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(text = "이용중인 상품", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        ProductItem(name = "헬스 3개월", duration = "2023.07.28 - 2024.03.27", progress = "잔여 163일/28회")
        ProductItem(name = "P.T 20회", duration = "2023.07.28 - 2024.01.27", progress = "잔여 103일/9회")
        ProductItem(name = "운동복 3개월", duration = "2023.07.28 - 2024.03.27", progress = "잔여 163일")
    }
}

@Composable
fun ProductItem(name: String, duration: String, progress: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = name, fontWeight = FontWeight.Bold)
        Text(text = duration, color = Color.Gray)
        Text(text = progress, color = Color.Gray)
    }
}

@Composable
fun Actions() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = { /* TODO: Payment link */ }) {
            Text(text = "결제링크 전송하기")
        }
        Button(onClick = { /* TODO: Product allocation */ }) {
            Text(text = "상품 배정하기")
        }
    }
}

@Composable
fun ContractScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "계약서 내용이 여기에 표시됩니다.", fontSize = 18.sp)
    }
}


