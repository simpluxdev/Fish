package com.simplux.fish.adapter

import androidx.recyclerview.widget.DiffUtil
import com.simplux.fish.model.DateCard

class DateCardDiffItemCallback: DiffUtil.ItemCallback<DateCard>() {

    override fun areItemsTheSame(oldItem: DateCard, newItem: DateCard): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DateCard, newItem: DateCard): Boolean {
        return oldItem == newItem
    }
}