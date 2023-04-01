package com.simplux.fish.viewmodel

import android.app.DatePickerDialog
import android.widget.DatePicker
import java.text.DateFormat
import java.util.*

class DatePickerData(private val saveViewModel: SaveViewModel): DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val mCalendar = Calendar.getInstance()
        mCalendar.set(Calendar.YEAR,year)
        mCalendar.set(Calendar.MONTH,month)
        mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        mCalendar.set(Calendar.HOUR_OF_DAY,0)
        mCalendar.set(Calendar.MINUTE,0)
        mCalendar.set(Calendar.SECOND,0)
        mCalendar.set(Calendar.MILLISECOND,0)
        val fullDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.time)
        saveViewModel.getFullDate(fullDate)
        val dateInMillis = mCalendar.timeInMillis
        saveViewModel.getSelectedDateInMillis(dateInMillis)
    }
}