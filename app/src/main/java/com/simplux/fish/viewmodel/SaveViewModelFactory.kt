package com.simplux.fish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplux.fish.model.DateCardDao
class SaveViewModelFactory(private val dateCardDao: DateCardDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SaveViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SaveViewModel(dateCardDao) as T
        }
        throw IllegalStateException("Unknown ViewModel object!")
    }
}