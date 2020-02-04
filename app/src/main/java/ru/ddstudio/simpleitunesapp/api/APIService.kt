package ru.ddstudio.simpleitunesapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.data.remote.album.BaseResponse

interface APIService{
    /**
     * Поиск album по любым параметрам с поиковым запросом "jack-johnson
     * https://itunes.apple.com/search?term=jack+johnson&entity=album
     */
    @GET("search")
    suspend fun getAlbumsBySearch(
        @Query("term") searchQuery: String,
        @Query("entity") typeEntity: String = "album") : Response<BaseResponse<Album>>


    /**
     * Поиск songs по параметру albumTerm (название альбома)
     * https://itunes.apple.com/search?entity=song&attribute=albumTerm&term=Forever Changes (2015 Remastered Version)
     */
    @GET("search")
    suspend fun getSongsByAlbumTerm(
        @Query("term") searchQuery: String,
        @Query("entity") typeEntity: String = "song",
        @Query("attribute") attribute: String = "albumTerm"
    )

}