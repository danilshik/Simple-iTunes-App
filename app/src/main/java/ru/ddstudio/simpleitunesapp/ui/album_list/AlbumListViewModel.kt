package ru.ddstudio.simpleitunesapp.ui.album_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.repositories.AlbumRepository
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(private val repository: AlbumRepository): ViewModel(){
    private var albums : LiveData<Result<List<Album>>> = getAlbumsBySearch("")

    private fun getAlbumsBySearch(searchQuery: String): LiveData<Result<List<Album>>>{
        return repository.getAlbumsBySearch(searchQuery)}

    fun getAlbums(): LiveData<Result<List<Album>>>{
        Log.d("AlbumListViewModel", albums.value.toString())
        return albums
    }
    fun handleSearchQuery(text: String?) {
         albums = if (text != null){getAlbumsBySearch(text)
        } else{
            getAlbumsBySearch("")
        }
        Log.d("AlbumListViewModel SV", text.toString())
    }



}