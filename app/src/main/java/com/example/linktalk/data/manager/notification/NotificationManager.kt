package com.example.linktalk.data.manager.notification

interface NotificationManager {
    suspend fun getToken(): String
}