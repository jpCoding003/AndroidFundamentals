package com.tops.androidfundamentals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.androidfundamentals.databinding.ActivityMainBinding
import com.tops.androidfundamentals.model.NewProduct
import com.tops.androidfundamentals.model.ProductRoot
import com.tops.androidfundamentals.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)

        addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(
                menu: Menu,
                menuInflater: MenuInflater
            ) {
                menuInflater.inflate(R.menu.product_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_new){
                    val intent = Intent(application, DataOfRetrofitApi::class.java)
                    startActivity(intent)
                    return true
                }
                return false
            }
        })

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