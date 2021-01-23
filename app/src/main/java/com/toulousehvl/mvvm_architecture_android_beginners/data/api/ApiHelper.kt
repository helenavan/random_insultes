package com.toulousehvl.mvvm_architecture_android_beginners.data.api

class ApiHelper(private val apiService: ApiService) {
    //fun getUsers() = apiService.getInsult()
    fun getInsults() = apiService.getInsult()
}