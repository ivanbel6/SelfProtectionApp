package com.example.selfprotectionapp.data

import jakarta.mail.Folder
import jakarta.mail.Session
import jakarta.mail.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.inject.Inject


class MailDataSource @Inject constructor() {
    suspend fun fetchMessages(token: String? = null): List<String> = withContext(Dispatchers.IO) {
        try {
            val props = Properties().apply {
                put("mail.imap.host", "imap.mail.ru")
                put("mail.imap.port", "993")
                put("mail.imap.ssl.enable", "true")
                put("mail.imap.auth.mechanisms", "XOAUTH2")
            }
            val session = Session.getDefaultInstance(props)
            val store: Store = session.getStore("imap")
            // Используем OAuth-токен
            store.connect("imap.mail.ru", "username@mail.ru", token ?: "mock_token")
            val inbox: Folder = store.getFolder("INBOX")
            inbox.open(Folder.READ_ONLY)
            val messages = inbox.messages.mapNotNull { it.subject }
            inbox.close()
            store.close()
            messages
        } catch (e: Exception) {
            emptyList()
        }
    }
}