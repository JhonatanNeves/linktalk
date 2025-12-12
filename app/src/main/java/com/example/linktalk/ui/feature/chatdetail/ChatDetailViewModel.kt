package com.example.linktalk.ui.feature.chatdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.example.linktalk.data.repository.ChatRepository
import com.example.linktalk.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val chatDetailRoute = savedStateHandle.toRoute<Route.ChatDetailRoute>()

    var messageText by mutableStateOf("")
        private set

    val pagingChatMessage = chatRepository.getPagedMessages(
        chatDetailRoute.userId
    ).cachedIn(viewModelScope)

    fun onMessageChange(message: String) {
        messageText = message
    }

    fun sendMessage() {
        viewModelScope.launch {
            chatRepository.sendMessage(
                receiverId = chatDetailRoute.userId,
                message = messageText,
            )
        }
    }
}