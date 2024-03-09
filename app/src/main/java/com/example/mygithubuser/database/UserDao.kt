package com.example.mygithubuser.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("SELECT * FROM userentity ORDER BY username ASC")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM userentity WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<UserEntity>
}