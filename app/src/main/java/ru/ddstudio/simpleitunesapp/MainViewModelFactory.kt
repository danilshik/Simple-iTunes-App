package ru.ddstudio.simpleitunesapp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ddstudio.simpleitunesapp.repositories.AlbumRepository
import ru.ddstudio.simpleitunesapp.ui.album_list.AlbumListViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val recordRepository : AlbumRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AlbumListViewModel::class.java)){
            return AlbumListViewModel(recordRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class. See MainViewModelFactory.kt")
    }
}
