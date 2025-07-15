package com.tops.androidfundamentals.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import com.tops.androidfundamentals.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnlogin.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
             //   replace<LoginFragment>(R.id.fragment_container_view)
            }
        }

        binding.btnsignup.setOnClickListener {

            if (binding.etFirst!= null && binding.etLastname!= null && binding.etPassword!= null && binding.etUsername!= null){

            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
              //  replace<LoginFragment>(R.id.fragment_container_view)
            }
            }else {
                Toast.makeText( context,    "Enter valid Details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}