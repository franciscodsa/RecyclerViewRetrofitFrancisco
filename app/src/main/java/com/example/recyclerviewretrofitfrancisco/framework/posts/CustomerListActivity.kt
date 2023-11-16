package com.example.recyclerviewretrofitfrancisco.framework.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.recyclerviewretrofitfrancisco.R
import com.example.recyclerviewretrofitfrancisco.databinding.PostListActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerListActivity : AppCompatActivity() {

    private lateinit var binding: PostListActivityBinding

    private val viewModel : CustomerListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_list_activity)
    }
}