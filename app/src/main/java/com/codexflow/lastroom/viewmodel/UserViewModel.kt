package com.codexflow.lastroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.codexflow.lastroom.room.UserRoomDatabase
import com.codexflow.lastroom.model.User
import com.codexflow.lastroom.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) :
    AndroidViewModel(application) {


    val repository: UserRepository
    val allstore: LiveData<List<User>>

    val name: LiveData<List<String>>
    val surname: LiveData<List<String>>
    val age: LiveData<List<Int>>


    init {
        val userDao = UserRoomDatabase.getDatabase(application, viewModelScope).userDao()
        repository = UserRepository(userDao)
        allstore = repository.alluserssaved
        name = repository.name
        surname = repository.surname
        age = repository.age

    }


    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }
}
