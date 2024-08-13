package com.example.groupware.managerScreen

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.groupware.connectDB.sendImgServer

data class ManagerInfo(
    var success: String = "",
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var address: String = "",
    var phone: String = "",
    var point: String = "0",
    var registration: String = "",
    var type: String = "",
    var picture1: Bitmap? = null,
    var picture2: Bitmap? = null,
    var picture3: Bitmap? = null,
    var picture4: Bitmap? = null,
    var picture5: Bitmap? = null,
)

@Composable
fun ManreScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var registration by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var fileUploaded by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }
    var managerInfo = ManagerInfo()
    val context = LocalContext.current
    val filePaths = mutableListOf("", "", "", "", "")
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            // 클릭한 uri가 List로 넘어옴
            uris.forEachIndexed { index, uri ->
                filePaths.add(uri.toString())
                when(index){
                    0 -> filePaths[0] = uri.toString()
                    1 -> filePaths[1] = uri.toString()
                    2 -> filePaths[2] = uri.toString()
                    3 -> filePaths[3] = uri.toString()
                    4 -> filePaths[4] = uri.toString()
                }
            }
        }
    // Use LazyColumn for scrolling
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "회원가입",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Fit you 제휴 시설일 경우, 다짐파트너 계정으로 월 19,000원 (VAT 별도)에\nFit you 이용이 가능합니다. Fit you 알아보기",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputField(
                value = email,
                onValueChange = { email = it },
                label = "아이디(이메일)",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputField(
                value = password,
                onValueChange = { password = it },
                label = "비밀번호",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "비밀번호 확인",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputField(value = name, onValueChange = { name = it }, label = "이름")
            Spacer(modifier = Modifier.height(8.dp))
            InputField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "연락처",
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = address, onValueChange = { address = it }, label = "주소")
            Spacer(modifier = Modifier.height(16.dp))
            InputField(
                value = registration,
                onValueChange = { registration = it },
                label = "사업자 등록번호"
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = type, onValueChange = { type = it }, label = "센터 유형")
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            // Image Selection Button
            Button(
                onClick = {
                    launcher.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "이미지 선택")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Business Registration Upload
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("사업자등록증 업로드")
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { fileUploaded = !fileUploaded }) {
                    Icon(imageVector = Icons.Default.FileUpload, contentDescription = "Upload File")
                }
                if (fileUploaded) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "File Uploaded",
                        tint = Color.Green
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            // Terms and Conditions Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it }
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "이용약관 및 개인정보취급방침에 동의합니다.")
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Next Button
            Button(
                onClick = {
                    managerInfo = managerInfo.copy(
                        email = email,
                        name = name,
                        address = address,
                        phone = phoneNumber,
                        registration = registration,
                        type = type
                    )
                    println("managerInfo: $managerInfo")
                    sendImgServer(filePaths, managerInfo, context)
//                    navController.navigate("ManagerLoginScreen")
                },
                enabled = termsAccepted,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "다음")
            }
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = icon?.let { { Icon(imageVector = it, contentDescription = null) } },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        singleLine = true,
        shape = RoundedCornerShape(8.dp)
    )
}