package com.example.groupware.gymScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun FilterDialog(onDismiss: () -> Unit) {
    var showCategoryDialog by remember { mutableStateOf(false) }
    var showPriceDialog by remember { mutableStateOf(false) }
    var showOtherDialog by remember { mutableStateOf(false) }

    // State variables to hold selected values
    var selectedCategory by remember { mutableStateOf("선택한 운동종목 없음") }
    var selectedPrice by remember { mutableStateOf("선택한 가격 없음") }
    var selectedOther by remember { mutableStateOf("선택한 기타 옵션 없음") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { showCategoryDialog = true }) {
                        Text("운동종목", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    TextButton(onClick = { showPriceDialog = true }) {
                        Text("가격", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    TextButton(onClick = { showOtherDialog = true }) {
                        Text("기타", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }

                // Display selected filters
                Spacer(modifier = Modifier.height(16.dp))
                Text("선택한 운동종목: $selectedCategory", fontSize = 16.sp)
                Text("선택한 가격: $selectedPrice", fontSize = 16.sp)
                Text("선택한 기타 옵션: $selectedOther", fontSize = 16.sp)

                // Display respective dialogs
                if (showCategoryDialog) {
                    FilterCategoryDialog(onDismiss = { showCategoryDialog = false }) { category ->
                        selectedCategory = category.joinToString(", ")
                    }
                }
                if (showPriceDialog) {
                    FilterPriceDialog(onDismiss = { showPriceDialog = false }) { price ->
                        selectedPrice = price
                    }
                }
                if (showOtherDialog) {
                    FilterOtherDialog(onDismiss = { showOtherDialog = false }) { other ->
                        selectedOther = other
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons to apply or cancel the filter
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("취소")
                    }
                    Button(onClick = {
                        // Apply action logic here
                        onDismiss()
                    }) {
                        Text("적용하기")
                    }
                }
            }
        }
    }
}

@Composable
fun FilterCategoryDialog(onDismiss: () -> Unit, onCategorySelected: (List<String>) -> Unit) {
    val options = listOf(
        "헬스", "P.T", "요가", "필라테스", "복싱", "크로스핏",
        "클라이밍", "골프", "무술/격투", "수영/수상",
        "댄스/체조/다이어트", "구기/라켓", "사이클/스케이트", "사우나", "관리"
    )
    val selectedOptions = remember { mutableStateListOf<String>() }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("운동종목", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(16.dp))

                options.chunked(2).forEach { rowOptions ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        rowOptions.forEach { option ->
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        if (selectedOptions.contains(option)) {
                                            selectedOptions.remove(option)
                                        } else {
                                            selectedOptions.add(option)
                                        }
                                    }
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = selectedOptions.contains(option),
                                    onCheckedChange = { isChecked ->
                                        if (isChecked) {
                                            selectedOptions.add(option)
                                        } else {
                                            selectedOptions.remove(option)
                                        }
                                    }
                                )
                                Text(text = option)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("취소")
                    }
                    Button(onClick = {
                        onCategorySelected(selectedOptions)
                        onDismiss()
                    }) {
                        Text("적용하기")
                    }
                }
            }
        }
    }
}

@Composable
fun FilterPriceDialog(onDismiss: () -> Unit, onPriceSelected: (String) -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("가격", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(16.dp))

                // Price Slider Implementation
                var priceRange by remember { mutableStateOf(0f..200f) }
                var frequencyRange by remember { mutableStateOf(2f..200f) }
                var selectedPeriod by remember { mutableStateOf("전체 기간") }

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "가격", fontSize = 16.sp)

                    Slider(
                        value = priceRange.start,
                        onValueChange = { newValue ->
                            priceRange = newValue..priceRange.endInclusive
                        },
                        valueRange = 0f..200f,
                        steps = 200,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${priceRange.start.toInt()}만원", fontSize = 14.sp)
                        Text(text = "${priceRange.endInclusive.toInt()}만원 이상", fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Period & Frequency Sections
                    Text("기간으로 이용할 수 있는 시설 보기", fontSize = 16.sp)

                    Row(modifier = Modifier.fillMaxWidth()) {
                        RadioButton(
                            selected = selectedPeriod == "전체 기간",
                            onClick = { selectedPeriod = "전체 기간" },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                        )
                        Text(text = "전체 기간", modifier = Modifier.align(Alignment.CenterVertically))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf("1개월", "3개월", "6개월", "12개월").forEach { period ->
                            Row {
                                RadioButton(
                                    selected = selectedPeriod == period,
                                    onClick = { selectedPeriod = period },
                                    colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                                )
                                Text(text = period, modifier = Modifier.align(Alignment.CenterVertically))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("횟수로 이용할 수 있는 시설 보기", fontSize = 16.sp)

                    Slider(
                        value = frequencyRange.start,
                        onValueChange = { newValue ->
                            frequencyRange = newValue..frequencyRange.endInclusive
                        },
                        valueRange = 2f..200f,
                        steps = 198,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${frequencyRange.start.toInt()}회", fontSize = 14.sp)
                        Text(text = "${frequencyRange.endInclusive.toInt()}회 이상", fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("취소")
                    }
                    Button(onClick = {
                        onPriceSelected("${priceRange.start.toInt()}만원 ~ ${priceRange.endInclusive.toInt()}만원 이상")
                        onDismiss()
                    }) {
                        Text("적용하기")
                    }
                }
            }
        }
    }
}

@Composable
fun FilterOtherDialog(onDismiss: () -> Unit, onOtherSelected: (String) -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("기타", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(16.dp))

                // Implement 기타 filtering options here...
                val otherOption = remember { mutableStateOf("기타 옵션 없음") }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("취소")
                    }
                    Button(onClick = {
                        onOtherSelected(otherOption.value)
                        onDismiss()
                    }) {
                        Text("적용하기")
                    }
                }
            }
        }
    }
}