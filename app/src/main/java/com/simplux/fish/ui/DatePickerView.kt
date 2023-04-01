package com.simplux.fish.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.simplux.fish.viewmodel.DatePickerData
import com.simplux.fish.viewmodel.SaveViewModel
import java.util.*
import java.util.concurrent.TimeUnit
class DatePickerView(private val saveViewModel: SaveViewModel): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(requireContext(),DatePickerData(saveViewModel),year,month,day)
        dialog.datePicker.minDate = Date().time + TimeUnit.DAYS.toMillis(1)
        return dialog
    }
}