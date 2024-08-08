package com.example.groupware.connectDB

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.groupware.R

@Composable
fun DbConnect() {
    Column {
        var userID by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var level by remember { mutableStateOf("") }
        val requestQueue: RequestQueue = Volley.newRequestQueue(LocalContext.current)
        val context = LocalContext.current

        val responseListener = Response.Listener<String> { response ->
            // Handle response here
            println("Response: $response")
        }
        val errorListener = Response.ErrorListener { error ->
            // Handle error here
            println("Error: ${error.message}")
        }
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
                //클릭한 uri가 List로 넘어옴
                sendImgServer(uris, context)
//                val registerRequest = CenterRegistRequest(
//                    "ttttest",
//                    "test",
//                    2,
//                    "ss",
//                    "sss",
//                    "ss",
//                    "Y",
//                    "1",
//                    "fityoucenterimage/picture1.png",
//                    "fityoucenterimage/picture1.png",
//                    "fityoucenterimage/picture1.png",
//                    "fityoucenterimage/picture1.png",
//                    "fityoucenterimage/picture1.png",
//                    responseListener, errorListener)
//                requestQueue.add(registerRequest)
            }
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .clickable {
                    launcher.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.gym), contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Image(
                painter = painterResource(id = R.drawable.gym), contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Image(
                painter = painterResource(id = R.drawable.gym), contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
        }

        Button(onClick = {
            val registerRequest = SignUpRequest("userID", "password", 1, "","","",responseListener, errorListener)
            requestQueue.add(registerRequest)
        }) {
            Text("Register")
        }
    }
}
