package com.example.recyclerviewretrofitfrancisco.data.model


import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class CustomerResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("birthDate")
    val birthDate : LocalDate,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String
)
