package com.example.room.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val datasource: UserDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {          //ViewModel'a değişken vermek için kullanılır
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(datasource, application) as T
        }
        throw IllegalArgumentException("Bilinmeyen ViewModel class'ı")
    }
}