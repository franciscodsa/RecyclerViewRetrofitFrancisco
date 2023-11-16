package com.example.recyclerviewretrofitfrancisco.framework.customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recyclerviewretrofitfrancisco.databinding.CustomerListActivityBinding
import com.example.recyclerviewretrofitfrancisco.domain.model.Customer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerListActivity : AppCompatActivity() {

    private lateinit var binding: CustomerListActivityBinding

    private var primeraVez: Boolean = false
    private var anteriorState: CustomerListState? = null
    private lateinit var customerListAdapter: CustomerAdapter
    private val viewModel: CustomerListViewModel by viewModels()

    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        primeraVez = true
        binding = CustomerListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customerListAdapter = CustomerAdapter(this,
            object : CustomerAdapter.CustomerActions {
                override fun onDelete(customer: Customer) {
                    TODO("Not yet implemented")
                }

                override fun onStartSelectedMode(customer: Customer) {
                    TODO("Not yet implemented")
                }

                override fun itemHasBeenClicked(customer: Customer) {
                    TODO("Not yet implemented")
                }
            })
        binding.recyclerViewCustomerList.adapter = customerListAdapter

        val touchHelper = ItemTouchHelper(customerListAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.recyclerViewCustomerList)

        viewModel.handleEvent(CustomerListEvent.GetAllCustomers)

        viewModel.uiState.observe(this){state ->
            if (state.customers != anteriorState?.customers && state.customers.isNotEmpty())
                customerListAdapter.submitList(state.customers)

            state.error?.let{
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.handleEvent(CustomerListEvent.ErrorVisto)
            }
        }
    }


}