package com.example.customerlist.network

import com.example.customerlist.model.CustomerDetailsResponse
import com.example.customerlist.model.CustomerListResponse
import com.example.customerlist.model.base.BaseResponse
import com.example.customerlist.utils.constant.ApiContant
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CustomerApiService {

    @GET(ApiContant.getCustomerDetails)
    suspend fun getCustomerList(@QueryMap map:HashMap<String,Int> ):BaseResponse<List<CustomerDetailsResponse>>
    @GET
    suspend fun getCustomerList():BaseResponse<CustomerListResponse>
}