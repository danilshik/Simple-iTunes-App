package ru.ddstudio.simpleitunesapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.AppDatabase
import ru.ddstudio.simpleitunesapp.data.database.Song
import ru.ddstudio.simpleitunesapp.data.remote.song.SongRemoteDataSource
import ru.ddstudio.simpleitunesapp.extensions.resultLiveData
import javax.inject.Inject


class SongRepository @Inject constructor(private val remoteSource: SongRemoteDataSource, private val database: AppDatabase){

    fun getSongByCollectionId(collectionId: Long): LiveData<Result<List<Song>>> {
        val resultLiveData = resultLiveData(
            databaseQuery = {database.getSongDao().getSongByCollectionId(collectionId)},
            networkCall = {remoteSource.getSongsByCollectionId(collectionId)},
            saveCallResult = {database.getSongDao().insertAll(it.results)}
        )
        Log.d("SongRepository", resultLiveData.value.toString())
        return resultLiveData
    }


}