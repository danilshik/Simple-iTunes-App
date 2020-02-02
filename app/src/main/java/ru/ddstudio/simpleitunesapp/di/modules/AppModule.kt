package ru.ddstudio.simpleitunesapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.ddstudio.simpleitunesapp.AppDelegate
import ru.ddstudio.simpleitunesapp.MainViewModelFactory
import ru.ddstudio.simpleitunesapp.repositories.AlbumRepository
import javax.inject.Singleton


@Module
class AppModule(private val app: AppDelegate){

    @Provides
    @Singleton
    fun provideContext() : Context = app

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: AlbumRepository) = MainViewModelFactory(repository)
}