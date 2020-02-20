package com.arlanov.androidapp.vm

import androidx.lifecycle.ViewModel
import com.arlanov.androidapp.model.repository.DaggerRepositoryComponent
import com.arlanov.androidapp.model.repository.Repository
import kotlinx.coroutines.*
import javax.inject.Inject

class ListViewModel : ViewModel() {
    @Inject
    lateinit var repository: Repository

    private var myJob: Job? = null

    init {
        DaggerRepositoryComponent.create().inject(this)
    }

    fun get(query: String){
        myJob = CoroutineScope(Dispatchers.IO).launch {
            repository.get(query)
        }
    }

    fun getLiveData() = repository.getLiveData()

    override fun onCleared() {
        super.onCleared()
        myJob?.cancel()
    }
}
