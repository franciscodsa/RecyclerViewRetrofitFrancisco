package com.example.recyclerviewretrofitfrancisco.data.model

import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("postId")
    val postId : Int,
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name :String,
    @SerializedName("email")
    val email:String,
    @SerializedName("body")
    val body:String
)