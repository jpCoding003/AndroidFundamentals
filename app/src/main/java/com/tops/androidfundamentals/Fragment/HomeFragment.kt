package com.tops.androidfundamentals.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.tops.androidfundamentals.R
import com.tops.androidfundamentals.adapter.MyAdapter
import com.tops.androidfundamentals.adapter.ViewPagerAdapter
import com.tops.androidfundamentals.databinding.FragmentHomeBinding
import com.tops.androidfundamentals.viewmodel.EmployeVideModel
import kotlin.getValue

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private val employeviewmodel: EmployeVideModel by viewModels()
    private lateinit var adapter : MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = MyAdapter(mutableListOf(), { empId ->
            employeviewmodel.deleteEmployee(requireContext(), empId)
        }, { emp ->
            val bundle = Bundle().apply {
                putParcelable("employe", emp)
            }
            findNavController().navigate(R.id.action_homeFragment_to_addNewEmpFragment, bundle)
        })

        binding.rvEmpData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmpData.adapter = adapter

        // ✅ Observe data and update list
        employeviewmodel.employelist.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }

        // ✅ Load data
        employeviewmodel.loadEmployeData(requireContext())

        binding.btnaddemp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNewEmpFragment)
        }

    }

}