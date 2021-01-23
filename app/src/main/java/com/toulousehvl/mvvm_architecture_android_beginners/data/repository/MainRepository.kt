package com.toulousehvl.mvvm_architecture_android_beginners.data.repository

import android.util.Log
import com.toulousehvl.mvvm_architecture_android_beginners.data.api.ApiHelper
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.Insult
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {
/*    fun getUsers() : Single<List<User>>{
        Log.e("MainRepository", "getUsers function")
        return apiHelper.getUsers()
    }*/

    fun getInsults() : Single<Insult> {
        Log.e("MainRepository", "${apiHelper.getInsults()}")
        return apiHelper.getInsults()
    }

}