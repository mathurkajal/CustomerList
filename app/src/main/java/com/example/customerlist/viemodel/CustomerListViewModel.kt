package com.example.customerlist.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.customerlist.model.CustomerDetailsResponse
import com.example.customerlist.model.CustomerListResponse
import com.example.customerlist.model.base.BaseResponse
import com.example.customerlist.viemodel.base.BaseViewModel
import com.example.customerlist.repository.repositoryimpl.CustomerListRepoImpl
import com.example.customerlist.utils.enum.StatusCode
import kotlinx.coroutines.launch

class CustomerListViewModel(private val repository: CustomerListRepoImpl) : BaseViewModel() {
    private val _customerListResponse = MutableLiveData<List<CustomerDetailsResponse?>>()
    val customerList: LiveData<List<CustomerDetailsResponse?>> get() = _customerListResponse


    suspend fun getCustomerList(pageNo: Int, pageSize: Int, UnitId: Int) {
        viewModelScope.launch {
            setLoading(true)
            val queryMap = hashMapOf(
                "pageno" to pageNo,
                "pagesize" to pageSize,
                "UnitId" to UnitId
            )
            try {
                val response = repository.getCustomerList(queryMap)
                if (response.statusCode == StatusCode.Success.code && response.data != null)
                    response.data.let {
                        _customerListResponse.value = it
                    }
                else setError(response.message ?: "Unknown Error")
            } catch (e: Exception) {
                setError(e.message)
            } finally {
                setLoading(false)
            }

        }

    }


}