package ru.netology.nmedia.dto

import androidx.annotation.DrawableRes

data class Post(
    val id: Long,
    val author: String,
    @DrawableRes val authorAvatar: Int,
    val published: String,
    val content: String,
    val likes: Int = 0,
    val likedByMe: Boolean = false,
    val reposts: Int = 0,
    val views: Int = 0,
)
