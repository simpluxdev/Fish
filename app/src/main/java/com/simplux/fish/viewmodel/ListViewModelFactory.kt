package com.simplux.fish.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplux.fish.model.DateCardDao
class ListViewModelFactory(private val dateCardDao: DateCardDao, private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(dateCardDao,context) as T
        }
        throw IllegalStateException("Unknown ViewModel object!")
    }
}