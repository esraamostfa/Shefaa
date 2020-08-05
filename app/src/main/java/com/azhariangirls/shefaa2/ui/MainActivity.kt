package com.azhariangirls.shefaa2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azhariangirls.shefaa2.R
import com.azhariangirls.shefaa2.model.Disease
import com.azhariangirls.shefaa2.viewmodel.DiseaseViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var diseaseViewModel: DiseaseViewModel
    private lateinit var pagerAdapter: ShefaaViewPagerAdapter
    private lateinit var diseases: MutableList<Disease>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diseaseViewModel = ViewModelProvider(this).get(DiseaseViewModel::class.java)
        diseases = diseaseViewModel.diseases

        pagerAdapter = ShefaaViewPagerAdapter(this, diseases.size)
        view_pager.adapter = pagerAdapter

        setTabLayout()
        setActionbarTitle()
    }

    private fun setActionbarTitle() {
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                supportActionBar?.title = diseases[position].title
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setTabLayout(){
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            val iconResource = resources
                .getIdentifier(diseases[position].uri, null, packageName)
            val iconDrawable = resources
                .getDrawable(iconResource)
            tab.icon = iconDrawable
        }.attach()
    }

}
