package com.example.recyclerviewretrofitfrancisco.domain.usecases

import com.example.recyclerviewretrofitfrancisco.data.repositories.CustomerRepository
import com.example.recyclerviewretrofitfrancisco.data.sources.remote.RemoteDataSource
import com.example.recyclerviewretrofitfrancisco.domain.model.Customer
import com.example.recyclerviewretrofitfrancisco.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class GetAllCustomerUsecase @Inject constructor(private val customerRepository: CustomerRepository){
    suspend operator fun invoke() : NetworkResult<List<Customer>> {
        return customerRepository.getAllCustomer()
    }
}