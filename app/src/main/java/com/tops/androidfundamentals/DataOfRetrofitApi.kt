package com.tops.androidfundamentals

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.androidfundamentals.databinding.ActivityDataOfRetrofitApiBinding
import com.tops.androidfundamentals.model.NewProduct
import com.tops.androidfundamentals.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DataOfRetrofitApi"
class DataOfRetrofitApi : AppCompatActivity() {
    private lateinit var binding: ActivityDataOfRetrofitApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataOfRetrofitApiBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        binding.btnSubmit.setOnClickListener {
            submitnewdata()
        }

    }

    private fun submitnewdata(){
        val product = NewProduct(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        val call:Call<NewProduct> = RetrofitClient.getInstance().saveProduct(product)
        call.enqueue(object: Callback<NewProduct>{
            override fun onResponse(
                call: Call<NewProduct?>,
                response: Response<NewProduct?>
            ) {
                Log.i(TAG, response.raw().toString())
                if(response.isSuccessful && response.raw().code == 201){
                    Log.i(TAG, response.body()!!.toString())
                }
            }

            override fun onFailure(
                call: Call<NewProduct?>,
                t: Throwable
            ) {
                Log.i(TAG,t.message.toString())
            }

        })
    }
}