package com.guru.ekacareassignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guru.ekacareassignment.data.User
import com.guru.ekacareassignment.data.UserDao
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers: LiveData<List<User>> get() = _allUsers

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun fetchAllUsers() {

        val users = withContext(Dispatchers.IO) {
            userDao.getAllUsers()
        }

        _allUsers.postValue(users)
    }
}
