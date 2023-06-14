package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [UserModel::class], version=1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract val userDao:UserDao
    companion object{
        @Volatile
        private var INSTANCE :UserDatabase?=null

        fun getDatabase(context: Context):UserDatabase?{
            synchronized(this){ //thredler
                var instance= INSTANCE

                if (instance==null) {
                    instance=Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return INSTANCE
            }
        }
    }
}