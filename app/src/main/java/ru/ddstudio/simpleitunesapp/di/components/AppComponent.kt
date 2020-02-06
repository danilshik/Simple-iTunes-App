package ru.ddstudio.simpleitunesapp.di.components

import dagger.Component
import ru.ddstudio.simpleitunesapp.di.modules.AppModule
import ru.ddstudio.simpleitunesapp.di.modules.DatabaseModule
import ru.ddstudio.simpleitunesapp.di.modules.NetworkModule
import ru.ddstudio.simpleitunesapp.ui.album.AlbumFragment
import ru.ddstudio.simpleitunesapp.ui.album_list.AlbumListFragment
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent{
    fun inject(fragment: AlbumListFragment)
    fun inject(fragment: AlbumFragment)

}