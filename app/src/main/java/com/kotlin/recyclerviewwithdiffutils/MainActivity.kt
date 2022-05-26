package com.kotlin.recyclerviewwithdiffutils

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.recyclerviewwithdiffutils.adapters.CustomAdapterEmployee
import com.kotlin.recyclerviewwithdiffutils.databinding.ActivityMainBinding
import com.kotlin.recyclerviewwithdiffutils.interfaces.OnItemClickListener
import com.kotlin.recyclerviewwithdiffutils.models.Employee


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var employeeList: ArrayList<Employee>
    private lateinit var adapter: CustomAdapterEmployee

    private fun initializations() {
        employeeList = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializations()
        initRecyclerView()
        fillList()

        binding.fabAddMain.setOnClickListener { onAddButtonClick() }
    }

    private fun initRecyclerView() {
        adapter = CustomAdapterEmployee()
        binding.rvEmployeesMain.adapter = adapter

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(itemPosition: Int) {
                Toast.makeText(this@MainActivity, employeeList[itemPosition].name, Toast.LENGTH_SHORT).show()
            }
        })
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

        employeeList.add(e1)
        employeeList.add(e2)
        employeeList.add(e3)
        employeeList.add(e4)
        employeeList.add(e5)
        employeeList.add(e6)
        employeeList.add(e7)
        employeeList.add(e8)

        adapter.submitList(employeeList)
        Toast.makeText(this@MainActivity, this.employeeList.size.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onAddButtonClick() {
        // Creating new temporary List
        val newList = ArrayList<Employee>()
        newList.addAll(employeeList)

        // Updating content & items
        newList[5].name = "Sohaib Ahmed"
        newList.add(Employee(9, "Employee : 9"))
        newList.add(Employee(9, "Employee : 9"))

        // Resubmitting newList
        adapter.submitList(newList)

        // update oldList with newList
        employeeList = newList.map { it.copy() } as ArrayList<Employee>
    }
    /*
    *   DiffUtils need two different lists
    *   1) To Update data, always submit new list (for new reference). So, it can match with old list.
    *   2) Update old list as well after each change.
    * */

}