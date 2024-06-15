package com.example.customerlist.model

import com.google.gson.annotations.SerializedName

data class CustomerDetailsResponse(
    @SerializedName("customerId") val customerId: Int,
    @SerializedName("fName") val firstName: String,
    @SerializedName("mName") val middleName: String?,
    @SerializedName("lName") val lastName: String,
    @SerializedName("rfName") val rfName: String?,
    @SerializedName("rmName") val rmName: String?,
    @SerializedName("rlName") val rlName: String?,
    @SerializedName("mobileNo") val mobileNo: String,
    @SerializedName("isCow") val isCow: Int,
    @SerializedName("isBuffalo") val isBuffalo: Int
)

data class PaginationDetails(
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("pageCount") val pageCount: Int
)