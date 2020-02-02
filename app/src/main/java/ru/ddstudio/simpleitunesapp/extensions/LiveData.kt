package ru.ddstudio.simpleitunesapp.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import ru.ddstudio.simpleitunesapp.data.Result


fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit): LiveData<Result<T>> =
    liveData(Dispatchers.IO){
        emit(Result.loading())
        val databaseSource = databaseQuery.invoke().map { Result.success(it) }
        emitSource(databaseSource)

        val responseSource = networkCall.invoke()
        if(responseSource.status == Result.Status.SUCCESS)
            saveCallResult(responseSource.data!!)
        else if(responseSource.status == Result.Status.ERROR){
            emit(Result.error(responseSource.error))
            emitSource(databaseSource)
        }
    }
