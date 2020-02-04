package ru.ddstudio.simpleitunesapp.extensions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import ru.ddstudio.simpleitunesapp.data.Result


fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit): LiveData<Result<T>>{
        Log.d("resultLiveData", "input")
     val news = liveData(Dispatchers.IO){


        emit(Result.loading<T>())
        val responseSource = networkCall.invoke()
        Log.d("ResultLiveData Network", responseSource.toString())
        if(responseSource.status == Result.Status.SUCCESS) {
            saveCallResult(responseSource.data!!)
            Log.d("resultLiveData", "save database")
        }
        else if(responseSource.status == Result.Status.ERROR){
            emit(Result.error(responseSource.error))
            Log.d("resultLiveData", "error ${responseSource.error}")
        }
        val databaseSource = databaseQuery.invoke()
        Log.d("ResultLiveData Database", databaseSource.value.toString())
        emitSource(databaseSource.map { Result.success(it) })
    }
    Log.d("resultLiveDataAll", news.toString())
    return news
}


