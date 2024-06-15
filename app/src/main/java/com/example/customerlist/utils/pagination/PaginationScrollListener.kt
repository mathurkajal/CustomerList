package com.example.customerlist.utils.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager?) : RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager!!.itemCount
        val lastVisibleItem = layoutManager!!.findLastVisibleItemPosition()

        if (getOffset() == 0 && totalItemCount < getLimit()) {
        } else if (!isLoading() && totalItemCount <= lastVisibleItem + 1) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun getOffset(): Int

    abstract fun getLimit(): Int

    abstract fun isLoading(): Boolean
}