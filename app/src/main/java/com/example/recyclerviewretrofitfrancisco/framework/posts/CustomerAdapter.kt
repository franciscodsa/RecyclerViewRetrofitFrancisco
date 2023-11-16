package com.example.recyclerviewretrofitfrancisco.framework.posts

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
import com.example.recyclerviewretrofitfrancisco.databinding.ViewPostBinding
import com.example.recyclerviewretrofitfrancisco.domain.model.Post

class CustomerAdapter(
    val context: Context,
    val actions : PostActions
) : ListAdapter<Post, CustomerAdapter.ItemViewholder>(DiffCallback()){

    interface PostActions{
        fun onDelete(post : Post)
        fun onStartSelectedMode(post: Post)
        fun itemHasBeenClicked(post: Post)

    }

    private var selectedPostsList = mutableListOf<Post>()
    private var selectedMode : Boolean = false

    fun startSelectedMode(){
        selectedMode = true
        notifyDataSetChanged()
    }

    fun resetSelectedMode(){
        selectedMode = false
        selectedPostsList.clear()
        notifyDataSetChanged()
    }

    fun setSelectedItems(selectedPosts : List<Post>){
        selectedPostsList.clear()
        selectedPostsList.addAll(selectedPosts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewholder{
        return ItemViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding = ViewPostBinding.bind(itemView)

        fun bind(item : Post){
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
                            selectedPostsList.add(item)
                        }else{
                            item.isSelected = false
                            itemView.setBackgroundColor(Color.WHITE)
                            selectedPostsList.remove(item)
                        }
                        actions.itemHasBeenClicked(item)
                    }
                }

                tvNombre.text= item.title
                tvId.text= item.id.toString()

                if (selectedMode){
                    selected.visibility = View.VISIBLE
                }else{
                    item.isSelected=false
                    selected.visibility= View.GONE
                }

                if (selectedPostsList.contains(item)){
                    itemView.setBackgroundColor(Color.GREEN)
                    binding.selected.isChecked = true
                }else {
                    itemView.setBackgroundColor(Color.WHITE)
                    binding.selected.isChecked=false
                }

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //if (!selectedMode) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    selectedPostsList.remove(currentList[viewHolder.adapterPosition])
                    actions.onDelete(currentList[viewHolder.adapterPosition])
                    if (selectedMode)
                        actions.itemHasBeenClicked(currentList[viewHolder.adapterPosition])
                }
            }
            //}
        }
    }

}