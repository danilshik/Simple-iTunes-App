package ru.ddstudio.simpleitunesapp.ui.album_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.extensions.mutableLiveData
import ru.ddstudio.simpleitunesapp.repositories.AlbumRepository
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(private val repository: AlbumRepository): ViewModel(){
    private var query = mutableLiveData("")
    private var albums : LiveData<Result<List<Album>>> = Transformations.switchMap(query, ::getAlbumsBySearch)

    private fun getAlbumsBySearch(searchQuery: String): LiveData<Result<List<Album>>>{
        return repository.getAlbumsBySearch(searchQuery)}

    fun getAlbums(): LiveData<Result<List<Album>>>{
        Log.d("AlbumListViewModel", albums.value.toString())
        return albums
    }
    fun handleSearchQuery(text: String?) {
        query.value = text
        Log.d("AlbumListViewModel SV", text.toString())
    }



}