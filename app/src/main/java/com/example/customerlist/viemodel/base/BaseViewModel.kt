package com.example.customerlist.viemodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * BaseViewModel for common for all
 */
abstract class BaseViewModel:ViewModel() {

    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    protected val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    protected fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }

    protected fun setError(errorMessage: String?) {
        _error.value = errorMessage
    }

}