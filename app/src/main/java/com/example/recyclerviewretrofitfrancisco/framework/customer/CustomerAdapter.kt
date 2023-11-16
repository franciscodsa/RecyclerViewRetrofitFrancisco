package com.example.recyclerviewretrofitfrancisco.framework.customer

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewretrofitfrancisco.R
import com.example.recyclerviewretrofitfrancisco.databinding.ViewCustomerListBinding
import com.example.recyclerviewretrofitfrancisco.domain.model.Customer

class CustomerAdapter(
    val context: Context,
    val actions : CustomerActions
) : ListAdapter<Customer, CustomerAdapter.ItemViewholder>(DiffCallback()){

    interface CustomerActions{
        fun onDelete(customer : Customer)
        fun onStartSelectedMode(customer: Customer)
        fun itemHasBeenClicked(customer: Customer)

    }

    private var selectedCustomersList = mutableListOf<Customer>()
    private var selectedMode : Boolean = false

    fun startSelectedMode(){
        selectedMode = true
        notifyDataSetChanged()
    }

    fun resetSelectedMode(){
        selectedMode = false
        selectedCustomersList.clear()
        notifyDataSetChanged()
    }

    fun setSelectedItems(selectedCustomer : List<Customer>){
        selectedCustomersList.clear()
        selectedCustomersList.addAll(selectedCustomer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewholder{
        return ItemViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_customer_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding = ViewCustomerListBinding.bind(itemView)
        fun bind(item : Customer){
            itemView.setOnClickListener{
                if (!selectedMode){
                    actions.onStartSelectedMode(item)

                }
                true
            }
            with(binding){
                selected.setOnClickListener{
                    if (selectedMode){
                        if (binding.selected.isChecked){
                            item.isSelected = true
                            itemView.setBackgroundColor(Color.GREEN)
                            selectedCustomersList.add(item)
                        }else{
                            item.isSelected = false
                            itemView.setBackgroundColor(Color.WHITE)
                            selectedCustomersList.remove(item)
                        }
                        actions.itemHasBeenClicked(item)
                    }
                }

                tvNombre.text= item.firstName
                tvId.text= item.id.toString()

                if (selectedMode){
                    selected.visibility = View.VISIBLE
                }else{
                    item.isSelected=false
                    selected.visibility= View.GONE
                }

                if (selectedCustomersList.contains(item)){
                    itemView.setBackgroundColor(Color.GREEN)
                    binding.selected.isChecked = true
                }else {
                    itemView.setBackgroundColor(Color.WHITE)
                    binding.selected.isChecked=false
                }

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //if (!selectedMode) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    selectedCustomersList.remove(currentList[viewHolder.adapterPosition])
                    actions.onDelete(currentList[viewHolder.adapterPosition])
                    if (selectedMode)
                        actions.itemHasBeenClicked(currentList[viewHolder.adapterPosition])
                }
            }
            //}
        }
    }

}