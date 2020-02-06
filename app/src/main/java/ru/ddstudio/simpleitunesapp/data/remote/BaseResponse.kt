package ru.ddstudio.simpleitunesapp.data.remote

data class BaseResponse<T>(
    val resultCount : Int,
    val results : List<T>
)