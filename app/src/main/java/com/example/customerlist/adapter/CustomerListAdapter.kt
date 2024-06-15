package com.example.customerlist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customerlist.R
import com.example.customerlist.databinding.CustomerDetailsListItemviewBinding
import com.example.customerlist.model.CustomerDetailsResponse
import com.example.customerlist.utils.Common


class CustomerListAdapter(
    private val context: Context,
    private var list: MutableList<CustomerDetailsResponse?>,
    private val listener: Listener
) : RecyclerView.Adapter<CustomerListAdapter.MyHolder>() {

    private lateinit var binding: CustomerDetailsListItemviewBinding
    private var filteredPersonList: MutableList<CustomerDetailsResponse?> = list



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(context)
        binding = CustomerDetailsListItemviewBinding.inflate(inflater, parent, false)
        return MyHolder(binding)
    }


    override fun onBindViewHolder(holder:MyHolder, position: Int) {

        holder.bindData(filteredPersonList[position]!!,position)

    }

    override fun getItemCount(): Int {

        return filteredPersonList.size
    }


     inner class MyHolder(private val binding: CustomerDetailsListItemviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(list:CustomerDetailsResponse,pos:Int) {
            val color = Common.getColour(pos)
            binding.customerNameTv.invalidate()
            binding.customerNameTv.text = list.firstName
            binding.customerNameTv.invalidate()
            binding.customerNameTv.setTextColor(color)
            binding.txMobile.text = list.mobileNo
            if (list.isCow == 1) {
                binding.customerImage.setImageResource(R.drawable.cow)
            } else {
                binding.customerImage.setImageResource(R.drawable.buffalo)
            }

            binding.root.setOnClickListener {
                listener.onItemClicked(list)
            }
        }
    }




    /**Logics */
    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String) {

        val lowerCaseQuery = query.lowercase()

        filteredPersonList = if (lowerCaseQuery.isEmpty()) { list
        } else {
            list.filter { it!!.firstName.lowercase().startsWith(query.lowercase()) ||
                    it.mobileNo.startsWith(query) }.toMutableList()
        }

        notifyDataSetChanged()

    }



    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list: List<CustomerDetailsResponse?>, pageNo: Int) {
        if (list.isEmpty())
            return
        if (pageNo == 1) {
            this.list.addAll(list)
            notifyDataSetChanged()
        } else {
            val startPos = this.list.size
            this.list.addAll(list)
            notifyItemRangeChanged(startPos, list.size - 1)
        }
        this.filteredPersonList = this.list.toMutableList()

    }

    /**
     * use for manage the item click on the recyclerView
     */
    interface Listener {
        fun onItemClicked(details: CustomerDetailsResponse)

    }
}