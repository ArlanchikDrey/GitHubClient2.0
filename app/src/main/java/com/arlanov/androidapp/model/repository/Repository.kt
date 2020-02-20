package com.arlanov.androidapp.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.arlanov.androidapp.model.models.RepositoryModel
import com.arlanov.androidapp.model.models.RepositoryModelWrapper
import com.arlanov.androidapp.model.models.mapWrapperRepository
import com.arlanov.androidapp.model.repository.api.Connection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor() {
    private val repositoriesApi = Connection.getApiRepositories()
    private val liveData =  MutableLiveData<List<RepositoryModelWrapper>>()

    companion object{
        const val TAG = "Repository"
    }

    fun get(query: String){
        repositoriesApi.getRepositories(query).enqueue(object : Callback<RepositoryModel>{
            override fun onFailure(call: Call<RepositoryModel>, t: Throwable) {
                Log.d(TAG, "onFailure ${t.message}")
            }

            override fun onResponse(
                call: Call<RepositoryModel>,
                response: Response<RepositoryModel>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null) {
                        val list = mutableListOf<RepositoryModelWrapper>()

                        response.body()?.items?.forEach {
                            list.add(mapWrapperRepository(it))
                        }
                        liveData.postValue(list)
                    }
                }
            }

        })
    }

    fun getLiveData() = liveData
}