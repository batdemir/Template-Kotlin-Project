package com.batdemir.template.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batdemir.template.R
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.ItemActionBinding

class ActionAdapter(
    private val itemListener: ItemListener? = null,
    private val longItemListener: LongItemListener? = null
) : ListAdapter<ActionItemModel, ActionAdapter.ActionViewHolder>(Companion) {

    class ActionViewHolder(val binding: ItemActionBinding) : RecyclerView.ViewHolder(binding.root)

    interface ItemListener {
        fun onClick(model: ActionItemModel)
    }

    interface LongItemListener {
        fun onClick(model: ActionItemModel)
    }

    companion object : DiffUtil.ItemCallback<ActionItemModel>() {
        override fun areItemsTheSame(
            oldItem: ActionItemModel,
            newItem: ActionItemModel
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: ActionItemModel,
            newItem: ActionItemModel
        ): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemActionBinding>(
                layoutInflater,
                R.layout.item_action,
                parent,
                false
            )
        return ActionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        val current = getItem(position)
        if (current.isSelected)
            lastSelectedPosition = position
        holder.binding.model = current
        holder.binding.root.setOnClickListener {
            current.isSelected = true
            if (lastSelectedPosition >= 0 && lastSelectedPosition != position) {
                val before = getItem(lastSelectedPosition)
                before.isSelected = false
            }
            lastSelectedPosition = position
            notifyDataSetChanged()
            itemListener?.onClick(current)
        }
        holder.binding.root.setOnLongClickListener {
            longItemListener?.onClick(current)
            true
        }
        holder.binding.executePendingBindings()
    }

    private var lastSelectedPosition = -1
}