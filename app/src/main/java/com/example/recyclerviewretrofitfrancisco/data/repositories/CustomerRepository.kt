package com.example.recyclerviewretrofitfrancisco.data.repositories

import com.example.recyclerviewretrofitfrancisco.data.sources.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@ActivityRetainedScoped
class CustomerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){
    suspend fun getAllCustomer()=
        with(Dispatchers.IO){
            remoteDataSource.
        }
}