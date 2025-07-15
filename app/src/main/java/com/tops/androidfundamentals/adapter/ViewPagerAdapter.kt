package com.tops.androidfundamentals.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tops.androidfundamentals.Fragment.HomeFragment
import com.tops.androidfundamentals.Fragment.LoginFragment
import com.tops.androidfundamentals.Fragment.SignupFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> HomeFragment()
            1-> LoginFragment()
            2-> SignupFragment()

            else -> LoginFragment()
        }
    }

    override fun getItemCount(): Int = 3
}