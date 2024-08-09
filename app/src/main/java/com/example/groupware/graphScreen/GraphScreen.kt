package com.example.groupware.graphScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groupware.R

class GraphScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGraph()
        }
    }
}

@Composable
fun MyGraph() {
    // 예시 데이터
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val visitCounts = listOf(50, 70, 30, 90, 100, 120, 80)
    val sales = listOf(2000, 3000, 1500, 5000, 4500, 6000, 3000)

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 상단 바와 뒤로가기 버튼, 로고
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 8.dp)  // 바의 두께를 약간 더 두껍게 조정
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { /* 뒤로가기 버튼 동작 */ },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp) // 뒤로가기 버튼 크기를 줄임
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // 로고를 가운데 배치
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(50.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            // 상단 바와 그래프 사이에 구분선을 추가
            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // 방문 회원수 그래프
            ColumnChart(
                days = days,
                values = visitCounts,
                label = "방문 회원수",
                barColor = Color.Blue
            )

            // "방문 회원수" 레이블 추가
            Text(
                text = "방문 회원수",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 매출 그래프
            ColumnChart(
                days = days,
                values = sales,
                label = "매출",
                barColor = Color(0xFF003366) // 남색
            )

            // "매출" 레이블 추가
            Text(
                text = "매출",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ColumnChart(days: List<String>, values: List<Int>, label: String, barColor: Color) {
    val maxValue = values.maxOrNull() ?: 0
    val barWidth = 30.dp
    val yAxisValues = 5 // Y축 값 표시 갯수

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp) // 그래프의 높이 지정
    ) {
        // Y축 값 표시 (아래에서 위로 증가하도록 수정)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            for (i in yAxisValues downTo 0) { // 위에서 아래로 그리기 대신 아래에서 위로
                val value = maxValue * i / yAxisValues
                androidx.compose.material3.Text(text = value.toString(), fontSize = 12.sp)
            }
        }

        Canvas(modifier = Modifier
            .fillMaxSize()
        ) {
            val chartHeight = size.height
            val barAreaWidth = size.width / days.size

            // 그리드 라인 그리기 (아래에서 위로 증가하도록 변경)
            for (i in 0..yAxisValues) {
                val y = chartHeight * i / yAxisValues
                drawLine(
                    color = Color.Gray,
                    start = androidx.compose.ui.geometry.Offset(0f, chartHeight - y),
                    end = androidx.compose.ui.geometry.Offset(size.width, chartHeight - y),
                    strokeWidth = 1.dp.toPx()
                )
            }

            // 막대 및 레이블 그리기
            for (i in days.indices) {
                val xCenter = i * barAreaWidth + barAreaWidth / 2

                // 모서리가 둥근 막대 그리기
                drawRoundRect(
                    color = barColor,
                    topLeft = androidx.compose.ui.geometry.Offset(xCenter - barWidth.toPx() / 2, chartHeight - (values[i].toFloat() / maxValue) * chartHeight),
                    size = androidx.compose.ui.geometry.Size(barWidth.toPx(), (values[i].toFloat() / maxValue) * chartHeight),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(10.dp.toPx()) // 모서리 둥글게
                )

                // 막대 위에 수치 텍스트
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        values[i].toString(),
                        xCenter,
                        chartHeight - (values[i].toFloat() / maxValue) * chartHeight - 10,
                        android.graphics.Paint().apply {
                            color = android.graphics.Color.BLACK
                            textSize = 30f
                            textAlign = android.graphics.Paint.Align.CENTER
                        }
                    )
                }

                // 요일 레이블 그리기
                drawContext.canvas.nativeCanvas.apply {
                    drawText(days[i], xCenter, chartHeight + 30f, android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 30f
                        textAlign = android.graphics.Paint.Align.CENTER
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGraphScreen() {
    MyGraph()
}
