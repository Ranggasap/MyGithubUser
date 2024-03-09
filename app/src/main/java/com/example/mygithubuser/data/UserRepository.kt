package com.example.mygithubuser.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.newsapp.utils.AppExecutors
import com.example.mygithubuser.database.UserDao
import com.example.mygithubuser.database.UserEntity
import com.example.mygithubuser.database.UserRoomDatabase

class UserRepository(application: Application) {
    private val mUserDao: UserDao
    private lateinit var executors: AppExecutors

    init {
        val db = UserRoomDatabase.getDatabase(application)
        mUserDao = db.userDao()
        executors = AppExecutors()
    }

    fun getAllUsers(): LiveData<List<UserEntity>> = mUserDao.getAllUsers()

    fun insert(userEntity: UserEntity){
        executors.diskIO.execute{
            mUserDao.insert(userEntity)
        }
    }

    fun delete(userEntity: UserEntity){
        executors.diskIO.execute{
            mUserDao.delete(userEntity)
        }
    }

    fun update(userEntity: UserEntity){
        executors.diskIO.execute{
            mUserDao.update(userEntity)
        }
    }

    fun getFavoriteUserByUsername(username: String): LiveData<UserEntity>{
        return mUserDao.getFavoriteUserByUsername(username)
    }
}