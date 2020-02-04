package ru.ddstudio.simpleitunesapp.data.remote.album

import ru.ddstudio.simpleitunesapp.data.database.Album

data class BaseResponse<T>(
    val resultCount : Int,
    val results : List<T>
)