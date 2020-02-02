package ru.ddstudio.simpleitunesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ddstudio.simpleitunesapp.repositories.AlbumRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: AlbumRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}