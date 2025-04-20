package com.example.selfprotectionapp.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.selfprotectionapp.R



@Composable
fun TrainingScreen(viewModel: TrainingViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val animatedProgress by animateFloatAsState(targetValue = state.progress.toFloat() / 100)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Обучение",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Круговая диаграмма с анимацией
        CircularProgressIndicator(progress = animatedProgress)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Прогресс обучения: ${state.progress}%",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Выберите подозрительное сообщение:",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))

        state.options.forEachIndexed { index, option ->
            Button(
                onClick = { viewModel.selectOption(index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.selectedOption == index)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(option, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(visible = state.result.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_training),
                        contentDescription = "Result",
                        tint = if (state.result.contains("Правильно")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = state.result,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun CircularProgressIndicator(progress: Float) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.size(100.dp)) {
        val strokeWidth = 10f
        drawCircle(
            color = Color.Gray,
            radius = size.minDimension / 2 - strokeWidth / 2,
            style = Stroke(width = strokeWidth)
        )
        drawArc(
            color = primaryColor,
            startAngle = -90f,
            sweepAngle = 360 * progress,
            useCenter = false,
            topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
            size = Size(size.width - strokeWidth, size.height - strokeWidth),
            style = Stroke(width = strokeWidth)
        )
    }
}