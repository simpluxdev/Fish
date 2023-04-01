package com.simplux.fish.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.ContentView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplux.fish.R
import com.simplux.fish.model.DateCard
import com.simplux.fish.model.DateCardDao
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
@SuppressLint("StaticFieldLeak")
class ListViewModel(private val dateCardDao: DateCardDao, private val context: Context): ViewModel() {
    val dateCards = dateCardDao.dateCards()
    fun calculateTimeLeft(dateCardDate: Long): String {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)
        calendar.set(Calendar.MILLISECOND,0)

        val currentDate = calendar.timeInMillis
        val remaining = dateCardDate - currentDate

        return if(TimeUnit.MILLISECONDS.toDays(remaining) <= 0) {
            context.getString(R.string.finished)
        } else {
            val left = TimeUnit.MILLISECONDS.toDays(remaining).toString()
            return "$left ${context.getString(R.string.day)}"
        }
    }
    fun showFullDate(dateCardDate: Long): String {
        return DateFormat.getDateInstance(DateFormat.FULL).format(dateCardDate)
    }
    fun removeDateCard(dateCard: DateCard) {
        viewModelScope.launch {
            dateCardDao.deleteDateCard(dateCard)
        }
    }
}