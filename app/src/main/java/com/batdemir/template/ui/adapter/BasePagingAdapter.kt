@file:Suppress("UNCHECKED_CAST")

package com.batdemir.template.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.batdemir.template.data.entities.RecyclerItem

class BasePagingAdapter<T : RecyclerItem, V : ViewDataBinding>(
    private val layoutId: Int,
    private var bindListener: BindListener<T, V>? = null,
    private var itemListener: ItemListener<T>? = null,
    private val enableSelectionEvent: Boolean = true,
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BaseViewHolder<V>>(diffCallback) {
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
            if (enableSelectionEvent)
                selectionEvent(position)
        }
        holderBase.binding.root.setOnLongClickListener {
            itemListener?.onLongClick(getMyItem(position))
            if (enableSelectionEvent)
                selectionEvent(position)
            true
        }
    }

    private fun selectionEvent(position: Int) {
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

    fun setBindListener(listener: BindListener<T, V>) {
        this.bindListener = listener
    }

    fun setItemListener(listener: ItemListener<T>) {
        this.itemListener = listener
    }
}