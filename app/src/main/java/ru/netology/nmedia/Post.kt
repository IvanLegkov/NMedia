package ru.netology.nmedia

import androidx.annotation.DrawableRes

data class Post(
    val id: Long,
    val author: String,
    @DrawableRes val authorAvatar: Int,
    val published: String,
    val content: String,
    var likes: Int = 0,
    var likedByMe: Boolean = false,
    var reposts: Int = 0,
    var views: Int = 0,
)
