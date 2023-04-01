package com.simplux.fish.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplux.fish.R
import com.simplux.fish.adapter.DateCardAdapter
import com.simplux.fish.databinding.FragmentListBinding
import com.simplux.fish.model.DateCardDatabase
import com.simplux.fish.viewmodel.ListViewModel
import com.simplux.fish.viewmodel.ListViewModelFactory
import com.simplux.fish.viewmodel.SaveViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentListBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val application = requireNotNull(this@ListFragment.activity).application
        val dateCardDao = DateCardDatabase.getInstance(application).dateCardDao
        val listViewModelFactory = ListViewModelFactory(dateCardDao,requireContext())
        val listViewModel = ViewModelProvider(this@ListFragment,listViewModelFactory)[ListViewModel::class.java]

        val dateCardAdapter = DateCardAdapter ({dateCardDate -> listViewModel.calculateTimeLeft(dateCardDate)},{dateCardDate -> listViewModel.showFullDate(dateCardDate) },{dateCard -> listViewModel.removeDateCard(dateCard) })

        binding.recyclerView.adapter = dateCardAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listViewModel.dateCards.observe(viewLifecycleOwner) {
            dateCardAdapter.submitList(it)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}