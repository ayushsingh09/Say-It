package com.example.sayit.models

data class Post(
    val text: String = " ",
    val creator: User = User(),
    val createdAt: Long = 0L, //system.minisecond
    val likedBy: ArrayList<String> = ArrayList()
)