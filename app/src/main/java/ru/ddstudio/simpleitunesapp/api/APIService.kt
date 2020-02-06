package ru.ddstudio.simpleitunesapp.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.data.remote.BaseResponse

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
     * Поиск album по collectionId и song в нем
     * https://itunes.apple.com/lookup?id=259510974&entity=song, где id - collectionId album
     */
    @GET("lookup")
    suspend fun getAlbumAndSongs(
        @Query("id") collectionId : String,
        @Query("entity") entity: String = "song"
    ) : Response<BaseResponse<JsonObject>>

}