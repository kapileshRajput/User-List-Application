package com.example.kapilesh.roomdatabaselib.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kapilesh.roomdatabaselib.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY _id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)
}