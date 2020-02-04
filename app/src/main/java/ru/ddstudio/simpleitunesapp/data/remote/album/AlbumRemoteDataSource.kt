package ru.ddstudio.simpleitunesapp.data.remote.album

import android.util.Log
import retrofit2.Response
import ru.ddstudio.simpleitunesapp.api.APIService
import ru.ddstudio.simpleitunesapp.api.BaseDataSource
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import javax.inject.Inject

class AlbumRemoteDataSource @Inject constructor(
    private val apiService: APIService
): BaseDataSource(){
    suspend fun getAlbumsBySearch(searchQuery: String): Result<BaseResponse<Album>>{
        val result = getResult{apiService.getAlbumsBySearch(searchQuery)}
        Log.d("AlbumRemoteDataSource", result.toString())
        return result
    }


}