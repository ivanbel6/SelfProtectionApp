package com.example.selfprotectionapp.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PermissionsScreen(viewModel: PermissionsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Разрешения",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Прогресс настройки: ${state.progress}%",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Для анализа сообщений нужны разрешения:",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))

        PermissionItem(
            title = "Уведомления",
            description = "Для перехвата сообщений из мессенджеров",
            isGranted = state.notificationPermission,
            onRequest = { viewModel.requestNotificationPermission() }
        )
        PermissionItem(
            title = "VK",
            description = "Для доступа к личным сообщениям",
            isGranted = state.vkPermission,
            onRequest = { viewModel.requestVkPermission() }
        )
        PermissionItem(
            title = "Mail.ru",
            description = "Для чтения личных писем",
            isGranted = state.mailPermission,
            onRequest = { viewModel.requestMailPermission() }
        )
        PermissionItem(
            title = "Telegram",
            description = "Для доступа к личным чатам",
            isGranted = state.telegramPermission,
            onRequest = { viewModel.requestTelegramPermission() }
        )
        PermissionItem(
            title = "Accessibility",
            description = "Для поведенческой аналитики",
            isGranted = state.accessibilityPermission,
            onRequest = { viewModel.requestAccessibilityPermission() }
        )
    }
}

@Composable
fun PermissionItem(
    title: String,
    description: String,
    isGranted: Boolean,
    onRequest: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Button(
                onClick = onRequest,
                enabled = !isGranted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isGranted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(if (isGranted) "Разрешено" else "Запросить")
            }
        }
    }
}