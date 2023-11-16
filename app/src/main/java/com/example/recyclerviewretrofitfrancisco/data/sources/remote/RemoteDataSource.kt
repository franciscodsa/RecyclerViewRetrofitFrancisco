package com.example.recyclerviewretrofitfrancisco.data.sources.remote

import com.example.recyclerviewretrofitfrancisco.data.model.BaseApiResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val customerService: CustomerService): BaseApiResponse(){
        suspend fun getAllCustomer() = safeApiCall(apiCall = {customerService.getAllCustomers()})
}