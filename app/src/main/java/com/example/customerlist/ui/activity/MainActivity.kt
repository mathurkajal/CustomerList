package com.example.customerlist.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customerlist.adapter.CustomerListAdapter
import com.example.customerlist.databinding.ActivityMainBinding
import com.example.customerlist.model.CustomerDetailsResponse
import com.example.customerlist.utils.constant.CommonConstant
import com.example.customerlist.utils.extension.setStatusBarDrawable
import com.example.customerlist.utils.pagination.PaginationScrollListener
import com.example.customerlist.viemodel.CustomerListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CustomerListAdapter.Listener {
    private val viewModels: CustomerListViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CustomerListAdapter
    private var OFFSET: Int = 1
    private var isDataLoading = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setStatusBarDrawable()
        initData()
        setObserver()
        getCustomerList(OFFSET, CommonConstant.pageSize, CommonConstant.UnitId)
        setScrollListener()

    }

    private fun initData() {
        adapter =
            CustomerListAdapter(this, ArrayList<CustomerDetailsResponse>().toMutableList(), this)
        binding.rv.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                isDataLoading=true
                adapter.filter(query?:"")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    isDataLoading=true //to manage the api don't hit during the search
                    adapter.filter(it)
                }
                return false
            }

        })


    }

    private fun setScrollListener() {
        binding.rv.addOnScrollListener(object :
            PaginationScrollListener(binding.rv.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isDataLoading = true
                OFFSET++
                getCustomerList(OFFSET)

            }

            override fun getOffset(): Int {
                return OFFSET
            }

            override fun getLimit(): Int {
                return CommonConstant.pageSize
            }

            override fun isLoading(): Boolean {
                return isDataLoading
            }
        })
    }

    private fun getCustomerList(
        pageNo: Int,
        pageSize: Int = CommonConstant.pageSize,
        UnitId: Int = CommonConstant.UnitId
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModels.getCustomerList(pageNo, pageSize, UnitId)
        }


    }

    private fun setObserver() {
        viewModels.customerList.observe(this) {
            if (it.isNullOrEmpty())
                return@observe
            isDataLoading = it.size < CommonConstant.pageSize
            adapter.updateItems(it, pageNo = OFFSET)

        }

        viewModels.error.observe(this) {
            binding.included.progressCircular.visibility = View.GONE

        }
        viewModels.loading.observe(this) {
            if (it) binding.included.progressCircular.visibility = View.VISIBLE else binding.included.progressCircular.visibility = View.GONE
        }
    }

    override fun onItemClicked(details: CustomerDetailsResponse) {
        Toast.makeText(this, details.firstName, Toast.LENGTH_SHORT).show()
    }



}

