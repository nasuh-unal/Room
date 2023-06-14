package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query(value="SELECT*FROM users ")
    suspend fun radAllData():List<UserModel>

    @Insert
    suspend fun addUser(userModel:UserModel)

    @Update
    suspend fun updateUser(userModel:UserModel)

    @Delete
    suspend fun deleteUser(userModel:UserModel)
}