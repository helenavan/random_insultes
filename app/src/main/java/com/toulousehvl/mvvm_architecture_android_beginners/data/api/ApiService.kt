package com.toulousehvl.mvvm_architecture_android_beginners.data.api

import com.toulousehvl.mvvm_architecture_android_beginners.data.model.Insult
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.User
import io.reactivex.Single

interface ApiService {
    //fun getUsers(): Single<List<User>>
    fun getInsult(): Single<Insult>
}