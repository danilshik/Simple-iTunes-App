package ru.ddstudio.simpleitunesapp.data

data class Result<out T>(
    val status : Status,
    val data: T?,
    val error: String?
){
    enum class Status{
        SUCCESS,
        LOADING,
        ERROR
    }

    companion object{
        fun <T> success(data: T) : Result<T> = Result(Status.SUCCESS, data, null)
        fun <T> error(error: String?, data: T? = null): Result<T> = Result(Status.ERROR, data, error)
        fun <T> loading(data: T? = null): Result<T> = Result(Status.LOADING, data, null)
    }

}