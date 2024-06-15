package com.example.customerlist.utils.enum


enum class StatusCode(val code: String) {
    Success("200"),
    FAILED("400"),
    NOT_FOUND("404"),
    UNAUTHORIZED("401");

}