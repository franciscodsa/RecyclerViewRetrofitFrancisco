package com.example.recyclerviewretrofitfrancisco.framework.customer

import com.example.recyclerviewretrofitfrancisco.domain.model.Customer

sealed class CustomerListEvent {
    object GetAllCustomers : CustomerListEvent()

    object ErrorVisto : CustomerListEvent()

    class DeleteCustomer(val customer: Customer) : CustomerListEvent()

    class SelectCustomer(val customer: Customer) : CustomerListEvent()

}