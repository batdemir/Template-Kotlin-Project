@file:Suppress("UNCHECKED_CAST")

package com.batdemir.template.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import com.batdemir.template.data.entities.RecyclerItem

class BasePagingAdapter<T : RecyclerItem, V : ViewDataBinding>(
    private val layoutId: Int,
    private var bindListener: BindListener<T, V>? = null,
    private var itemListener: ItemListener<T>? = null,
    private val enableSingleSelectionEvent: Boolean = true,
    private val singleSelectionEvent: () -> Unit = {},
) : PagingDataAdapter<RecyclerItem, BaseViewHolder<V>>(BASE_DIFF_UTIL) {
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<V>(layoutInflater, layoutId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holderBase: BaseViewHolder<V>, position: Int) {
        bindListener?.onBind(holderBase, getMyItem(position), position)
        holderBase.binding.root.setOnClickListener {
            itemListener?.onClick(getMyItem(position))
            if (enableSingleSelectionEvent)
                mySingleSelectionEvent(position)
        }
        holderBase.binding.root.setOnLongClickListener {
            itemListener?.onLongClick(getMyItem(position))
            if (enableSingleSelectionEvent)
                mySingleSelectionEvent(position)
            true
        }
    }

    private fun mySingleSelectionEvent(position: Int) {
        singleSelectionEvent()
        getMyItem(position).isSelected = true
        if (lastSelectedPosition >= 0 && lastSelectedPosition != position) {
            val before = getMyItem(lastSelectedPosition)
            before.isSelected = false
            notifyItemChanged(lastSelectedPosition)
        }
        lastSelectedPosition = position
        notifyItemChanged(position)
    }

    private fun getMyItem(position: Int): T {
        return getItem(position) as T
    }

    suspend fun mySummitData(pagingData: PagingData<T>) {
        submitData(pagingData as PagingData<RecyclerItem>)
    }
}