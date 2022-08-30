package com.kotlin.recyclerviewwithdiffutils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.recyclerviewwithdiffutils.R
import com.kotlin.recyclerviewwithdiffutils.databinding.ListItemEmployeeBinding
import com.kotlin.recyclerviewwithdiffutils.interfaces.OnItemClickListener
import com.kotlin.recyclerviewwithdiffutils.models.Employee

class CustomAdapterEmployee(private val onItemClickListener: OnItemClickListener) : ListAdapter<Employee, CustomAdapterEmployee.CustomViewHolderEmployee>(DiffUtilsEmployees) {

    object DiffUtilsEmployees : DiffUtil.ItemCallback<Employee>() {

        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }

    class CustomViewHolderEmployee(val binding: ListItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderEmployee {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemEmployeeBinding>(layoutInflater, R.layout.list_item_employee, parent, false)
        return CustomViewHolderEmployee(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolderEmployee, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.apply {
                employee = item
                adapterPosition = position
                itemClick = onItemClickListener
            }
        }
    }
}