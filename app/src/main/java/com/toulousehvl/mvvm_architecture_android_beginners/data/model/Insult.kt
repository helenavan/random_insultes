package com.toulousehvl.mvvm_architecture_android_beginners.data.model

import com.google.gson.annotations.SerializedName

data class Insult(
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("language")
    val language: String = "",
    @SerializedName("insult")
    val insult: String = ""
)
