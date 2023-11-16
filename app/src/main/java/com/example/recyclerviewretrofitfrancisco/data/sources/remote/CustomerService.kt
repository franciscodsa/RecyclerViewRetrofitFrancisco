package com.example.recyclerviewretrofitfrancisco.data.sources.remote


import com.example.recyclerviewretrofitfrancisco.domain.model.Customer
import retrofit2.Response
import retrofit2.http.GET

interface CustomerService {

    @GET("/customers")
    suspend fun getAllCustomers() : Response<List<Customer>>
}