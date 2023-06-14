package com.example.room.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel( userDao:UserDao, application: Application):AndroidViewModel(application) {

    var userDao=userDao
    var userModelListLiveData =MutableLiveData<List<UserModel>>()

    init {
        getAllUser()
    }

    private fun getAllUser() {
        viewModelScope.launch {
            userModelListLiveData.value=userDao.radAllData() //Veri Tabanındaki bütün verileri çekip listeye atama işlemi
        }
    }

    fun deleteUser(user:UserModel){
        viewModelScope.launch {
            userDao.deleteUser(user)                        //İletilen veriyi veri tabanına gönderip silmek
        }
    }

    fun addUser(user:UserModel){
        viewModelScope.launch {
            userDao.addUser(user)                           //İletilen veriyi veri tabanına gönderip kaydetmek
        }
    }

    fun updateUser(user: UserModel){
        viewModelScope.launch {
            userDao.updateUser(user)                        //İletilen veriyi güncellemek
        }
    }
}