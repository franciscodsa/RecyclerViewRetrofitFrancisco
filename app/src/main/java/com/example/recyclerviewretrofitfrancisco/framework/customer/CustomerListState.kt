package com.example.recyclerviewretrofitfrancisco.framework.customer

import com.example.recyclerviewretrofitfrancisco.domain.model.Customer

data class CustomerListState(
    val customers : List<Customer> = emptyList(),
    val error : String? = null
)