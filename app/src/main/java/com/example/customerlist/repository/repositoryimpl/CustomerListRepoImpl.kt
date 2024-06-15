package com.example.customerlist.repository.repositoryimpl

import com.example.customerlist.model.CustomerListResponse
import com.example.customerlist.model.base.BaseResponse
import com.example.customerlist.network.CustomerApiService
import com.example.customerlist.repository.CustomerListRepo

class CustomerListRepoImpl(private val apiService: CustomerApiService) : CustomerListRepo {

    override suspend fun getCustomerList(map: HashMap<String, Int>) =
        apiService.getCustomerList(map)

    override suspend fun getCustomerList(): BaseResponse<CustomerListResponse> =
        apiService.getCustomerList()


}