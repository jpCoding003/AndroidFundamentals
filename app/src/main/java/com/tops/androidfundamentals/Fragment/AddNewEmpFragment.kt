package com.tops.androidfundamentals.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tops.androidfundamentals.R
import com.tops.androidfundamentals.databinding.FragmentAddNewEmpBinding
import com.tops.androidfundamentals.viewmodel.EmployeVideModel


class AddNewEmpFragment : Fragment() {

    private lateinit var binding: FragmentAddNewEmpBinding
    private val employeviewmodel : EmployeVideModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewEmpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etnamae.text.toString()
            val role = binding.etrole.text.toString()

           employeviewmodel.addEmploye(requireContext(), name, role)
           findNavController().navigate(R.id.action_addNewEmpFragment_to_homeFragment)

        }




    }
}