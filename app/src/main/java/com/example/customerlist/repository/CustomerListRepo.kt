package com.example.customerlist.repository

import com.example.customerlist.model.CustomerDetailsResponse
import com.example.customerlist.model.CustomerListResponse
import com.example.customerlist.model.base.BaseResponse

interface CustomerListRepo {

    suspend fun getCustomerList(map:HashMap<String,Int>):BaseResponse<List<CustomerDetailsResponse>>
    suspend fun getCustomerList():BaseResponse<CustomerListResponse>


}

