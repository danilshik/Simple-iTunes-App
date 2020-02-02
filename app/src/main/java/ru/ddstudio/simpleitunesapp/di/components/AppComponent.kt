package ru.ddstudio.simpleitunesapp.di.components

import dagger.Component
import ru.ddstudio.simpleitunesapp.di.modules.AppModule
import ru.ddstudio.simpleitunesapp.di.modules.NetworkModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent{

}