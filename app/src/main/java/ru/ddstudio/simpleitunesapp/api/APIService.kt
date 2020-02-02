package ru.ddstudio.simpleitunesapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ddstudio.simpleitunesapp.data.remote.album.AlbumsResponse

interface APIService{
    //Поиск по типу Album и
    //https://itunes.apple.com/search?term=jack+johnson&entity=album
    @GET("search")
    suspend fun getAlbumsBySearch(
        @Query("term") searchQuery: String,
        @Query("entity") typeEntity: String = "album") : Response<AlbumsResponse>

}