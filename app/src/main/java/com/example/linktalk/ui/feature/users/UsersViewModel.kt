package com.example.linktalk.ui.feature.users

import androidx.lifecycle.ViewModel
import com.example.linktalk.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel() {
    val usersFlow = userRepository.getUsers()
}

