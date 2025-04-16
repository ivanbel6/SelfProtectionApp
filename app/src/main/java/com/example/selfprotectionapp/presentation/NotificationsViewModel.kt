package com.example.selfprotectionapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.selfprotectionapp.domain.Threat
import com.example.selfprotectionapp.domain.ThreatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    threatRepository: ThreatRepository
) : ViewModel() {
    val threats: StateFlow<List<Threat>> = threatRepository.getAllThreats()
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Lazily, emptyList())
}