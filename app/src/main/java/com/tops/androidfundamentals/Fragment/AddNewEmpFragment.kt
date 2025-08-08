package com.tops.androidfundamentals.Fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tops.androidfundamentals.R
import com.tops.androidfundamentals.databinding.FragmentAddNewEmpBinding
import com.tops.androidfundamentals.model.EmployeeModel
import com.tops.androidfundamentals.viewmodel.EmployeVideModel


class AddNewEmpFragment : Fragment() {

    private lateinit var binding: FragmentAddNewEmpBinding
    private val employeviewmodel : EmployeVideModel by viewModels()
    private var empId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewEmpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val emp = arguments?.getParcelable<EmployeeModel>("emp")        // Decrepted way ANDRIOD version less than TIRAMISU

        val employee = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("employe", EmployeeModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("employe")
        }

        empId = employee?.id
        binding.etnamae.setText(employee?.name)
        binding.etrole.setText(employee?.role)

        binding.btnSubmit.text = if (empId != null) "Update" else "Submit"

        binding.btnSubmit.setOnClickListener {
            val name = binding.etnamae.text.toString()
            val role = binding.etrole.text.toString()

            if (empId != null) {
                // ❗ FIX: Pass a new EmployeeModel with updated values
                val updatedEmp = EmployeeModel(empId!!, name, role)
                employeviewmodel.updateEmployee(requireContext(), updatedEmp)
            } else {
                employeviewmodel.addEmploye(requireContext(), name, role)
            }

            // ✅ Navigate back to HomeFragment
            findNavController().navigate(R.id.action_addNewEmpFragment_to_homeFragment)
        }
    }
}