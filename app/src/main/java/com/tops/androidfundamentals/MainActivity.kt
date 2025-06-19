package com.tops.androidfundamentals

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.androidfundamentals.model.ProductRoot
import com.tops.androidfundamentals.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val call: Call<ProductRoot> = RetrofitClient.getInstance().listproduct()

        call.enqueue(object: Callback<ProductRoot>{
            override fun onResponse(call: Call<ProductRoot?>,response: Response<ProductRoot?>
            ) {
               if (response.isSuccessful){
                   val data = response.body()
                   Log.i(TAG, "DATA == ${data.toString()}")
               }
            }

            override fun onFailure(
                call: Call<ProductRoot?>,
                t: Throwable
            ) {
                Log.i(TAG, t.message.toString())
            }

        })
    }
}