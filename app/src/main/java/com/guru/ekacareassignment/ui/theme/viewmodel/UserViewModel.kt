package com.guru.ekacareassignment.ui.theme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guru.ekacareassignment.data.User
import com.guru.ekacareassignment.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers

    init {
        fetchAllUser()
    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
        fetchAllUser()  // Refresh the user list after deletion
    }

    fun fetchAllUser() = viewModelScope.launch {
        repository.fetchAllUsers()
    }
}
