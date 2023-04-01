package com.simplux.fish.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.simplux.fish.R
import com.simplux.fish.databinding.FragmentSaveBinding
import com.simplux.fish.model.DateCardDatabase
import com.simplux.fish.viewmodel.SaveViewModel
import com.simplux.fish.viewmodel.SaveViewModelFactory
class SaveFragment : Fragment() {
    private var _binding: FragmentSaveBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentSaveBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val application = requireNotNull(this@SaveFragment.activity).application
        val dateCardDao = DateCardDatabase.getInstance(application).dateCardDao
        val saveViewModelFactory = SaveViewModelFactory(dateCardDao)
        val saveViewModel = ViewModelProvider(this@SaveFragment,saveViewModelFactory)[SaveViewModel::class.java]

        binding.buttonSelectDate.setOnClickListener {
            val newFragment = DatePickerView(saveViewModel)
            newFragment.show(parentFragmentManager,"date_picker")
        }

        saveViewModel.fullDate.observe(viewLifecycleOwner) {
            if(it.isNotBlank() || it.isNotEmpty()) {
                binding.buttonSelectDate.text = it
            }
        }

        binding.buttonSave.setOnClickListener {
            val description = binding.editTextDescription.text.toString()
            val selectedEmoji = binding.spinnerEmoji.selectedItem.toString()
            if(description.isBlank() || description.isBlank()) {
                Toast.makeText(context, context?.getString(R.string.descriptionWarning),Toast.LENGTH_SHORT).show()
            } else if (saveViewModel.fullDate.value?.isBlank() == true || saveViewModel.fullDate.value?.isBlank() == true) {
                Toast.makeText(context, context?.getString(R.string.dateWarning),Toast.LENGTH_SHORT).show()
            } else {
                saveViewModel.getDescription(description)
                saveViewModel.getSelectedEmoji(selectedEmoji)
                saveViewModel.saveDateCard()
            }
        }

        saveViewModel.isNavigate.observe(viewLifecycleOwner) {
            if(it) {
                findNavController().navigate(SaveFragmentDirections.actionSaveFragmentToListFragment())
                saveViewModel.isNavigated()
            }
        }

        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}