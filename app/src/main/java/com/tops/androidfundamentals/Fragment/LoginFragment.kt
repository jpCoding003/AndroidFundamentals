package com.tops.androidfundamentals.Fragment

import android.content.Intent
import android.os.Bundle 
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import com.tops.androidfundamentals.MainActivity
import com.tops.androidfundamentals.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = binding.etUsername.text.toString()
        var password = binding.etPassword.text.toString()

        binding.btnlogin.setOnClickListener {
            if (username == "jay" && password == "123") {
                var intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            else if(binding.etPassword!= null && binding.etUsername!= null) {
                var intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText( context, "Enter valid Details", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnsignup.setOnClickListener{
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
            }
        }
    }
}