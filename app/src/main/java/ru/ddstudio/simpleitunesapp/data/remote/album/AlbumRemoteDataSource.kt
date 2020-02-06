package ru.ddstudio.simpleitunesapp.data.remote.album

import android.util.Log
import ru.ddstudio.simpleitunesapp.api.APIService
import ru.ddstudio.simpleitunesapp.data.remote.BaseDataSource
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.data.remote.BaseResponse
import javax.inject.Inject

/**
 * Удаленный сервис для получения Album
 */
class AlbumRemoteDataSource @Inject constructor(
    private val apiService: APIService
): BaseDataSource(){
    suspend fun getAlbumsBySearch(searchQuery: String): Result<BaseResponse<Album>>{
        val result = getResult{apiService.getAlbumsBySearch(searchQuery)}
        Log.d("AlbumRemoteDataSource", result.toString())
        return result
    }


}