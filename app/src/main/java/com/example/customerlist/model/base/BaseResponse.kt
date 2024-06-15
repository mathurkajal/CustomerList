package com.example.customerlist.model.base

import com.example.customerlist.model.PaginationDetails
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>( val statusCode: String,
                            val message: String?,
                            @SerializedName("responseData") val  data: T?,
                            @SerializedName("responseData1") val paginationDetails: PaginationDetails): BaseResponseInterface

