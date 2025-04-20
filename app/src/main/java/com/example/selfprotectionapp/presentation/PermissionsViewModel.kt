package com.example.selfprotectionapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PermissionsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(PermissionState())
    val state: StateFlow<PermissionState> = _state

    fun requestNotificationPermission() {
        _state.value = _state.value.copy(notificationPermission = true)
        updateProgress()
    }

    fun requestVkPermission() {
        _state.value = _state.value.copy(vkPermission = true)
        updateProgress()
    }

    fun requestMailPermission() {
        _state.value = _state.value.copy(mailPermission = true)
        updateProgress()
    }

    fun requestTelegramPermission() {
        _state.value = _state.value.copy(telegramPermission = true)
        updateProgress()
    }

    fun requestAccessibilityPermission() {
        _state.value = _state.value.copy(accessibilityPermission = true)
        updateProgress()
    }

    private fun updateProgress() {
        val permissions = listOf(
            _state.value.notificationPermission,
            _state.value.vkPermission,
            _state.value.mailPermission,
            _state.value.telegramPermission,
            _state.value.accessibilityPermission
        )
        val grantedCount = permissions.count { it }
        _state.value = _state.value.copy(progress = (grantedCount * 100 / permissions.size))
    }
}

data class PermissionState(
    val notificationPermission: Boolean = false,
    val vkPermission: Boolean = false,
    val mailPermission: Boolean = false,
    val telegramPermission: Boolean = false,
    val accessibilityPermission: Boolean = false,
    val progress: Int = 0
)