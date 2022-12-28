package com.pop.apptest.iftest

import android.util.Log
import androidx.lifecycle.*
import com.pop.apptest.pr.PrRepository
import com.pop.apptest.pr.Result
import com.pop.apptest.pr.models.Line
import com.pop.apptest.pr.models.TimeByWO
import com.pop.apptest.pr.models.WorkerByTime
import kotlinx.coroutines.launch

class PrViewModel(private var repository: PrRepository) : ViewModel() {

    val plant = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
//    val lines = MutableLiveData<List<Line>>()
    val timesByWo = MutableLiveData<List<TimeByWO>>()
    val workerByTime = MutableLiveData<List<WorkerByTime>>()

    init {
        Log.d(Config.LOG_TAG, "PrViewmodel initialized....")
    }

    val linesWithPlant : LiveData<List<Line>> = plant.switchMap { param ->
        liveData {
            loading.value = true

            when( val result = repository.getLineInfo(param) ) {
                is Result.Success -> {
                    emit(result.data)
                }
                is Result.Fail -> {
                    Log.d(Config.LOG_TAG, result.toString())
                }
                is Result.Error -> {
                    Log.d(Config.LOG_TAG, result.toString())
                }
            }

            loading.value = false
        }
    }


    fun getTimesByWo(plant : String, dept : String, date : String) {

        loading.value = true

        viewModelScope.launch {
            when( val result = repository.getTimesByWo(plant, dept, date) ) {
                is Result.Success -> {
                    timesByWo.value = result.data
                }
                is Result.Fail -> {
                    Log.d(Config.LOG_TAG, result.toString())
                }
                is Result.Error -> {
                    Log.d(Config.LOG_TAG, result.toString())
                }
            }
            loading.value = false
        }
    }
    fun getWorkersByTime(plant : String, dept : String, date : String) {

        loading.value = true

        viewModelScope.launch {
            when( val result = repository.getWorkersByWo(plant, dept, date) ) {
                is Result.Success -> {
                    workerByTime.value = result.data
                }
                is Result.Fail -> {
                    Log.d(Config.LOG_TAG, result.toString())
                }
                is Result.Error -> {
                    Log.d(Config.LOG_TAG, result.toString())
                }
            }
            loading.value = false
        }
    }

    fun getAllInfo(plant : String, dept : String, date : String) {

        loading.value = true

        viewModelScope.launch {

            when( val result1 = repository.getTimesByWo(plant, dept, date) ) {
                is Result.Success -> {
                    timesByWo.value = result1.data
                }
                is Result.Fail -> {
                    Log.d(Config.LOG_TAG, result1.toString())
                }
                is Result.Error -> {
                    Log.d(Config.LOG_TAG, result1.toString())
                }
            }

            when( val result2 = repository.getWorkersByWo(plant, dept, date) ) {
                is Result.Success -> {
                    workerByTime.value = result2.data
                }
                is Result.Fail -> {
                    Log.d(Config.LOG_TAG, result2.toString())
                }
                is Result.Error -> {
                    Log.d(Config.LOG_TAG, result2.toString())
                }
            }

        }

        loading.value = false
    }


//    fun getTimesByWo(plant : String, dept : String, date : String){
//        loading.value = true
//
//        viewModelScope.launch {
//            val response = PrService.getService().getTimesByWo(plant, dept, date)
//            if (response.isSuccessful) {
//                timesByWo.value = response.body()
//                loading.value = false
//            } else {
//                loading.value = false
//            }
//        }
//    }
}