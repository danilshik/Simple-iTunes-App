package ru.ddstudio.simpleitunesapp.data.remote.song

import android.util.Log
import ru.ddstudio.simpleitunesapp.api.APIService
import ru.ddstudio.simpleitunesapp.data.remote.BaseDataSource
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Song
import ru.ddstudio.simpleitunesapp.data.remote.BaseResponse
import javax.inject.Inject


/**
 * Удаленный сервис для получения Song
 */
class SongRemoteDataSource @Inject constructor(
    private val apiService: APIService
): BaseDataSource(){
    suspend fun getSongsByCollectionId(collectionId: Long): Result<BaseResponse<Song>>{
        val result = getResultSong{apiService.getAlbumAndSongs(collectionId.toString())}


        Log.d("SongRemoteDataSource", result.toString())
        return result
    }


}