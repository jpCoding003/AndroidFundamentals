package com.tops.androidfundamentals

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.tops.androidfundamentals.adapter.ViewPagerAdapter
import com.tops.androidfundamentals.databinding.ActivityMainBinding

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


        val adapter = ViewPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tablayout,binding.viewpager){
                tab,position->
            tab.text = when(position){
                0-> "Home"
                1-> "Second"
                2-> "Thord"
                else -> "nothing "
            }
        }.attach()

    }

}