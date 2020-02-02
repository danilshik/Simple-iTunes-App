package ru.ddstudio.simpleitunesapp.data.remote.album

import ru.ddstudio.simpleitunesapp.api.APIService
import ru.ddstudio.simpleitunesapp.api.BaseDataSource
import javax.inject.Inject

class AlbumRemoteDataSource @Inject constructor(
    private val apiService: APIService
): BaseDataSource(){
    suspend fun fetchAlbumsBySearch(searchQuery: String) = getResult{
        apiService.getAlbumsBySearch(searchQuery)
    }
}