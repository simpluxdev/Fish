package com.simplux.fish.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplux.fish.model.DateCard
import com.simplux.fish.model.DateCardDao
import kotlinx.coroutines.launch
class SaveViewModel(private val dateCardDao: DateCardDao): ViewModel() {
    private val _fullDate = MutableLiveData("")
    val fullDate: LiveData<String>
        get() = _fullDate
    private var _selectedDateInMillis = 0L
    private var _selectedEmoji = ""
    private var _description = ""
    private var _isNavigate = MutableLiveData(false)
    val isNavigate: LiveData<Boolean>
        get() = _isNavigate
    fun getFullDate(fullDate:String) {
        _fullDate.value = fullDate
    }
    fun getSelectedDateInMillis(dateInMillis: Long) {
        _selectedDateInMillis = dateInMillis
    }
    fun getSelectedEmoji(selectedEmoji: String) {
        _selectedEmoji = selectedEmoji
    }
    fun getDescription(description: String) {
        _description = description
    }
    fun saveDateCard() {
        viewModelScope.launch {
            dateCardDao.insertDateCard(DateCard(0,_description, _selectedEmoji,_selectedDateInMillis))
            _isNavigate.value = true
        }
    }
    fun isNavigated() {
        _isNavigate.value = false
    }
}