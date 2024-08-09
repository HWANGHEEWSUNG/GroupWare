package com.example.groupware.profileScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.groupware.R

@Composable
fun GymProfileScreen(navController: NavController) {

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            //클릭한 uri가 List로 넘어옴
        }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "시설 정보 수정 페이지")
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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "운동시설 명\n" +
                        "어떤 운동 시설 (헬스장, 필라테스, 요가, 크로스핏, 실내 클라이밍, 테니스, 골프) -> 체크박스\n" +
                        "\n" +
                        "사진 최대 5개\n" +
                        "\n" +
                        "1일권 포인트 가격 (추후 3개월 할인 etc.)\n" +
                        "\n" +
                        "영업장 주소\n" +
                        "\n" +
                        "위치\n" +
                        "\n" +
                        "운영시간\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n"
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewGymProfileScreen() {
//    GymProfileScreen(navController = rememberNavController())
//}
//

