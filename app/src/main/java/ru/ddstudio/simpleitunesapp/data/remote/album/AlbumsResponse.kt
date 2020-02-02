package ru.ddstudio.simpleitunesapp.data.remote.album

import ru.ddstudio.simpleitunesapp.data.remote.album.Album

data class AlbumsResponse(
    val resultCount : Int,
    val results : List<Album>
)