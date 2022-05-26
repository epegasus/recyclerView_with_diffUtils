package com.kotlin.recyclerviewwithdiffutils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.recyclerviewwithdiffutils.R
import com.kotlin.recyclerviewwithdiffutils.databinding.ListItemEmployeeBinding
import com.kotlin.recyclerviewwithdiffutils.interfaces.OnItemClickListener
import com.kotlin.recyclerviewwithdiffutils.models.Employee

class CustomAdapterEmployee : ListAdapter<Employee, CustomAdapterEmployee.CustomViewHolderEmployee>(DiffUtilsEmployees) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class CustomViewHolderEmployee(itemView: View, onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val binding = ListItemEmployeeBinding.bind(itemView)

        init {
            itemView.setOnClickListener { onItemClickListener.onItemClick(adapterPosition) }
        }
    }

    object DiffUtilsEmployees : DiffUtil.ItemCallback<Employee>() {

        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderEmployee {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_employee, parent, false)
        return CustomViewHolderEmployee(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CustomViewHolderEmployee, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.apply {
                tvIdListItemEmployee.text = item.id.toString()
                tvTitleListItemEmployee.text = item.name
            }
        }
    }
}