package ru.ddstudio.simpleitunesapp.ui.album

import androidx.lifecycle.*
import ru.ddstudio.simpleitunesapp.data.database.Song
import ru.ddstudio.simpleitunesapp.repositories.SongRepository
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import javax.inject.Inject

class AlbumViewModel @Inject constructor(private val repository: SongRepository): ViewModel(){
    private val currentCollectionId = MutableLiveData<Long>(0)

    private var songs : LiveData<Result<List<Song>>> = Transformations.switchMap(currentCollectionId, ::getSongsByCollectionId)

    private fun getSongsByCollectionId(collectionId: Long): LiveData<Result<List<Song>>>{
        return repository.getSongByCollectionId(collectionId)}

    fun getSongData(): LiveData<Result<List<Song>>>{
        return songs
    }

    fun setAlbum(album: Album){
        currentCollectionId.value = album.collectionId
    }
}