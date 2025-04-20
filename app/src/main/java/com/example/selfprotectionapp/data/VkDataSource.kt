package com.example.selfprotectionapp.data

import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.requests.VKRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class VkDataSource @Inject constructor() {
    suspend fun fetchMessages(): List<String> = suspendCancellableCoroutine { continuation ->
        try {
            VK.execute(VKRequest("messages.getHistory"), object : VKApiCallback<Any> {
                override fun success(result: Any) {
                    // Упрощённый парсинг, реальная логика позже
                    val messages = listOf(
                        "Привет, срочно перейди по ссылке http://fake.com",
                        "Как дела?"
                    )
                    continuation.resume(messages)
                }

                override fun fail(error: Exception) {
                    continuation.resumeWithException(error)
                }
            })
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }
}