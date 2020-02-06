package ru.ddstudio.simpleitunesapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.data.database.AppDatabase
import ru.ddstudio.simpleitunesapp.data.remote.album.AlbumRemoteDataSource
import ru.ddstudio.simpleitunesapp.extensions.resultLiveData
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val remoteSource: AlbumRemoteDataSource, private val database: AppDatabase){

    fun getAlbumsBySearch(searchQuery: String): LiveData<Result<List<Album>>>{


        Log.d("RepositoryNew", searchQuery)
        val resultLiveData = resultLiveData(
            databaseQuery = {database.getAlbumDao().getAlbumsBySearch("%${searchQuery}%")},
            networkCall = {remoteSource.getAlbumsBySearch(searchQuery)},
            saveCallResult = {database.getAlbumDao().insertAll(it.results)}
        )
        Log.d("AlbumRepository", resultLiveData.value.toString())
        return resultLiveData
    }


}