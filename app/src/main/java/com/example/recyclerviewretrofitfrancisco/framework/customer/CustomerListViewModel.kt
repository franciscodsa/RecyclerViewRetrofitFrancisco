package com.example.recyclerviewretrofitfrancisco.framework.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewretrofitfrancisco.domain.model.Customer
import com.example.recyclerviewretrofitfrancisco.domain.usecases.GetAllCustomerUsecase
import com.example.recyclerviewretrofitfrancisco.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private  val getAllCustomerUsecase: GetAllCustomerUsecase
): ViewModel() {

    private val customersList = mutableListOf<Customer>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _uiState = MutableLiveData(CustomerListState())
    val uiState : LiveData<CustomerListState> get() = _uiState


    private fun getAllCustomer(){
        viewModelScope.launch {
            var result = getAllCustomerUsecase.invoke()

            when(result){
                is NetworkResult.Error -> _error.value = result.message?: ""
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> customersList.addAll(result.data?: emptyList())
            }

            _uiState.value = CustomerListState(customers = customersList.toList())
        }
    }

    fun handleEvent(event: CustomerListEvent){
        when(event){
            is CustomerListEvent.DeleteCustomer -> TODO()
            CustomerListEvent.GetAllCustomers -> getAllCustomer()
            is CustomerListEvent.SelectCustomer -> TODO()
            CustomerListEvent.ErrorVisto -> _uiState.value = _uiState.value?.copy(error = null)
        }
    }
}
