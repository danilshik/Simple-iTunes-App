package ru.ddstudio.simpleitunesapp.repositories

import ru.ddstudio.simpleitunesapp.extensions.resultLiveData
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val remoteSource: AlbumRepository, private val dao: AlbumDao){

    fun getAlbumsBySearch(searchQuery: String) = resultLiveData(
        databaseQuery = dao.getAlbumsBySearch(searchQuery),
        networkCall = {remoteSource.getAlbumsBySearch(searchQuery)},
        saveCallResult = {dao.insertAll(it.results)}
    )


}