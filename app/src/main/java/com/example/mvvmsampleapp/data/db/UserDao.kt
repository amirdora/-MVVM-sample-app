package com.example.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import com.example.mvvmsampleapp.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user : User) : Long

    @Query("Select * from user where uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>
}