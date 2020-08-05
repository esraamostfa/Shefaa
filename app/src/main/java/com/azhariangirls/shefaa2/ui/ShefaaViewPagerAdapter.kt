package com.azhariangirls.shefaa2.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azhariangirls.shefaa2.model.Disease

class ShefaaViewPagerAdapter(activity: AppCompatActivity, private val itemCount: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount() = itemCount

    override fun createFragment(position: Int) =
        HomeFragment.getInstance(position)

}