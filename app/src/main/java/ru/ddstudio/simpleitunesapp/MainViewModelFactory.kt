package ru.ddstudio.simpleitunesapp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ddstudio.simpleitunesapp.repositories.AlbumRepository
import ru.ddstudio.simpleitunesapp.repositories.SongRepository
import ru.ddstudio.simpleitunesapp.ui.album.AlbumViewModel
import ru.ddstudio.simpleitunesapp.ui.album_list.AlbumListViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val albumRepository : AlbumRepository,
                                               private val songRepository: SongRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AlbumListViewModel::class.java)){
            return AlbumListViewModel(albumRepository) as T
        }
        if(modelClass.isAssignableFrom(AlbumViewModel::class.java)){
            return AlbumViewModel(songRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class. See MainViewModelFactory.kt")
    }
}
