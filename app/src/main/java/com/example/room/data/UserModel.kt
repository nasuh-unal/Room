package com.example.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey(autoGenerate = true)//Biz herhangi bir işlem yapmadan veri tabanına işlem geldikçe sürekli kendi idlerini günceller
    val id: Int=0,
    var name: String,
    var age:Int ): Serializable