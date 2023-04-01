package com.simplux.fish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simplux.fish.databinding.DateCardItemBinding
import com.simplux.fish.model.DateCard

class DateCardAdapter(private val calculate: (dateCardDate: Long) -> String, private val show: (dateCardDate: Long) -> String, private val remove: (dateCard: DateCard) -> Unit): ListAdapter<DateCard, DateCardAdapter.DateCardViewHolder>(DateCardDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateCardViewHolder {
        return DateCardViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: DateCardViewHolder, position: Int) {
        val dateCard = getItem(position)
        holder.bind(dateCard,calculate,show,remove)
    }

    class DateCardViewHolder(private val binding: DateCardItemBinding): RecyclerView.ViewHolder(binding.root){

        companion object {
            fun inflateFrom(parent: ViewGroup): DateCardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DateCardItemBinding.inflate(layoutInflater,parent,false)
                return DateCardViewHolder(binding)
            }
        }

        fun bind(dateCard: DateCard, calculate: (dateCardDate: Long) -> String, show: (dateCardDate: Long) -> String, remove: (dateCard: DateCard) -> Unit) {
            binding.textViewShowEmoji.text = dateCard.emoji
            binding.textViewShowDescription.text = dateCard.description
            binding.textViewShowTimeLeft.text = calculate.invoke(dateCard.date)
            binding.textViewShowFullDate.text = show.invoke(dateCard.date)
            binding.imageViewDelete.setOnClickListener {
                remove.invoke(dateCard)
            }
        }
    }
}