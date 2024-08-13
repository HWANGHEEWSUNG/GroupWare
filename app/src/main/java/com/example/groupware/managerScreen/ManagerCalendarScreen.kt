package com.example.groupware.managerScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun ManagerCalendarScreen(navController: NavController) {
    Column {
        val currentDate = LocalDate.now()
        var year by remember { mutableIntStateOf(currentDate.year) }
        var month by remember { mutableIntStateOf(currentDate.monthValue) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDCE9EC))
        ) {
            CalendarHeader(year, month,
                onPreviousMonth = {
                    val (newYear, newMonth) = updateMonth(year, month, -1)
                    year = newYear
                    month = newMonth
                }, onNextMonth = {
                    val (newYear, newMonth) = updateMonth(year, month, 1)
                    year = newYear
                    month = newMonth
                })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .background(Color.White)
            ) {
                CalendarWeekHeader()

                CustomCalendarView(year, month) { day ->
                    DayContent(day)
                }
            }
        }

    }
}

@Composable
fun CalendarHeader(
    year: Int, month: Int, onPreviousMonth: () -> Unit, onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Image(imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    onPreviousMonth()
                }

        )
        Spacer(modifier = Modifier.weight(1f))
        val currentMonth = YearMonth.of(year, month).month.toString()
        Text(
            text = "$currentMonth   $year", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black
        )

        Spacer(modifier = Modifier.weight(1f))
        Image(imageVector = Icons.AutoMirrored.Filled.ArrowRight,
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    onNextMonth()
                })
        Spacer(modifier = Modifier.weight(2f))
    }
}

@Composable
fun CalendarWeekHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        val daysOfWeek = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
        daysOfWeek.forEach { day ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
private fun CustomCalendarView(
    year: Int,
    month: Int,
    content: @Composable (Int) -> Unit
) {
    Layout(modifier = Modifier
        .fillMaxSize()
        .drawWithCache {
            onDrawWithContent {
                drawContent()
            }
        }, content = {
        //한 칸에 텍스트&이미지 이므로 요일에 해당하는 수에 2배를 하고 -1을 함
        val firstDayOfMonth = (LocalDate.of(year, month, 1).dayOfWeek.value % 7) * 2 - 1
        val daysInMonth = YearMonth.of(year, month).lengthOfMonth()
        val totalCells = firstDayOfMonth + daysInMonth

        (0..totalCells).forEach { index ->
            if (index > firstDayOfMonth) {
                content(index - firstDayOfMonth)
            } else {
                Spacer(modifier = Modifier)
            }
        }
    }) { measurables, constraints ->
        val dayWidth = constraints.maxWidth / 7
        val dayHeight = constraints.maxHeight / 6

        val placeables = measurables.mapIndexed { idx, measurable ->
            measurable.measure(
                if (idx % 2 == 0) {
                    constraints.copy(
                        minWidth = dayWidth, maxWidth = dayWidth, minHeight = 40, maxHeight = 40
                    )
                } else {
                    constraints.copy(
                        minWidth = dayWidth,
                        maxWidth = dayWidth,
                        minHeight = dayHeight - 40,
                        maxHeight = dayHeight - 40
                    )
                }
            )
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEachIndexed { index, placeable ->
                val dayIndex = index / 2
                val row = dayIndex / 7
                val column = dayIndex % 7

                val xPosition = column * dayWidth
                val yPosition = row * dayHeight

                if (index % 2 == 0) {
                    placeable.place(x = xPosition, y = yPosition)
                } else {
                    placeable.place(x = xPosition, y = yPosition + 40)
                }
            }
        }
    }
}

@Composable
private fun DayContent(day: Int) {
    val currentDay = LocalDate.now().dayOfMonth
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        if (day == currentDay) {
            Box(
                modifier = Modifier
                    .size(32.dp) // 배경 박스의 크기를 지정합니다.
                    .background(
                        color = Color(0xFF50B4B0),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center // 텍스트를 중앙에 위치시킵니다.
            ) {
                Text(
                    text = "$day",
                    fontSize = 16.sp,
                    color = Color.White, // 현재 날짜 텍스트를 흰색으로 지정
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Text(
                text = "$day",
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                // 클릭 이벤트 처리
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "신청 회원", color = Color.Black)
    }
}

private fun updateMonth(year: Int, month: Int, delta: Int): Pair<Int, Int> {
    var newYear = year
    var newMonth = month + delta
    if (newMonth < 1) {
        newMonth = 12
        newYear--
    } else if (newMonth > 12) {
        newMonth = 1
        newYear++
    }
    return Pair(newYear, newMonth)
}

//
//@Preview(showBackground = true)
//@Composable
//fun PreviewManagerScreen() {
//    ManagerScreen(navController = rememberNavController())
//}
