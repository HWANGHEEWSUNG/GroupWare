package com.example.groupware.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.groupware.R

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Profile", fontSize = 30.sp)
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "profile")
            Column() {
                Text(text = "다히22 >")
                Text(text = "email")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()){
            Column {
                Text(text = "포인트")
                Text(text = "0P")
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(text = "찜")
                Text(text = "0개")
            }
        }
        Row(){

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "point", modifier = Modifier.size(80.dp))
                Text(text = "포인트")
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "pay", modifier = Modifier.size(80.dp))
                Text(text = "결제내역")
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "talk", modifier = Modifier.size(80.dp))
                Text(text = "상담톡")
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "review", modifier = Modifier.size(80.dp))
                Text(text = "후기관리")
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "어쩌구")
            Text(text = "어쩌구")
            Text(text = "어쩌구")
            Text(text = "로그아웃")
        }
    }
}