package com.kotlin.recyclerviewwithdiffutils

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.recyclerviewwithdiffutils.adapters.CustomAdapterEmployee
import com.kotlin.recyclerviewwithdiffutils.databinding.ActivityMainBinding
import com.kotlin.recyclerviewwithdiffutils.interfaces.OnItemClickListener
import com.kotlin.recyclerviewwithdiffutils.models.Employee


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: CustomAdapterEmployee

    private var employeeArrayList: ArrayList<Employee> = ArrayList()
    private val employeeList: List<Employee> get() = employeeArrayList.toList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        fillList()

        binding.fabAddMain.setOnClickListener { onAddButtonClick() }
    }

    private fun initRecyclerView() {
        adapter = CustomAdapterEmployee(object : OnItemClickListener {
            override fun onItemClick(itemPosition: Int) {
                Toast.makeText(this@MainActivity, employeeList[itemPosition].name, Toast.LENGTH_SHORT).show()
            }
        })
        binding.rvEmployeesMain.adapter = adapter
    }

    private fun fillList() {
        val e1 = Employee(1, "Employee : 1")
        val e2 = Employee(2, "Employee : 2")
        val e3 = Employee(3, "Employee : 3")
        val e4 = Employee(4, "Employee : 4")
        val e5 = Employee(5, "Employee : 5")
        val e6 = Employee(6, "Employee : 6")
        val e7 = Employee(7, "Employee : 7")
        val e8 = Employee(8, "Employee : 8")

        employeeArrayList.add(e1)
        employeeArrayList.add(e2)
        employeeArrayList.add(e3)
        employeeArrayList.add(e4)
        employeeArrayList.add(e5)
        employeeArrayList.add(e6)
        employeeArrayList.add(e7)
        employeeArrayList.add(e8)

        adapter.submitList(employeeList)
        Toast.makeText(this@MainActivity, employeeList.size.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onAddButtonClick() {
        employeeArrayList.add(Employee(9, "Employee : 9"))
        employeeArrayList.add(Employee(9, "Employee : 9"))

        // Resubmitting newList
        adapter.submitList(employeeList)
    }
}