package ru.ddstudio.simpleitunesapp.data.database


/**
 * Класс, от которого наследуются Song и Album
 */
abstract class BaseEntity {
    abstract var wrapperType: String
}