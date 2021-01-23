package com.toulousehvl.mvvm_architecture_android_beginners.data.api

import android.util.Log
import com.rx2androidnetworking.Rx2AndroidNetworking
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.Insult
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.User
import io.reactivex.Single

private const val TAG = "ApiServiceImpl"

class ApiServiceImpl : ApiService {
    override fun getInsult(): Single<Insult> {
        Log.e(TAG,"get en point")
        return Rx2AndroidNetworking.get("https://evilinsult.com/generate_insult.php?lang=en&type=json")
            .build()
            .getObjectSingle(Insult::class.java)
    }
/*    override fun getUsers(): Single<List<User>> {
        Log.e(TAG, "getUsers")
        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getObjectListSingle(User::class.java)
    }*/

}