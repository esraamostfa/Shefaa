package com.azhariangirls.shefaa2.ui


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.azhariangirls.shefaa2.R
import com.azhariangirls.shefaa2.model.Disease
import com.azhariangirls.shefaa2.model.DiseasesStore
import com.azhariangirls.shefaa2.model.Medicine
import com.azhariangirls.shefaa2.viewmodel.DiseaseViewModel
import com.azhariangirls.shefaa2.viewmodel.MedicineViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() , MedicineRecyclerViewAdapter.MedicineRecyclerViewAdapterListener{


    private val addAlarmActivityRequestCode = 1
    private var position: Int = 0
    private lateinit var diseases: MutableList<Disease>
    private lateinit var adapter: MedicineRecyclerViewAdapter
    private lateinit var medicineViewModel: MedicineViewModel
    private lateinit var diseaseViewModel: DiseaseViewModel

    companion object {

        const val EXTRA_MEDICINE_NAME = "EXTRA_MEDICINE_NAME"

        fun newReplyIntent(medicineName: String): Intent {
            val replyIntent = Intent()
            if (!TextUtils.isEmpty(medicineName)) {
                replyIntent.putExtra(EXTRA_MEDICINE_NAME, medicineName)
            }
            return replyIntent
        }

        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val homeFragment = HomeFragment()
            homeFragment.arguments = Bundle().apply {
                putInt(ARG_POSITION, position)
            }
            return homeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicineViewModel = ViewModelProvider(this).get(MedicineViewModel::class.java)
        //add an observer for the allMedicines LiveData property from the MedicineViewModel.
        //The onChanged() method (the default method for our Lambda) fires
        // when the observed data changes and the activity is in the foreground.
        medicineViewModel.allMedicines.observe(this, Observer {
            it?.let { adapter.updateMedicines(it) }
        })

        diseaseViewModel = ViewModelProvider(this).get(DiseaseViewModel::class.java)
        diseases = diseaseViewModel.diseases

        position = requireArguments().getInt(ARG_POSITION)
        setViews()
        setRecyclerView()
        onClickAddAlarm()
    }

    private fun setViews() {
        disease_details.text = diseases[position].title

        if (position == 0) {
            add_alarm_button.visibility = View.VISIBLE
            medicine_recycler_view.visibility = View.VISIBLE
            disease_details.visibility = View.GONE
        } else {
            add_alarm_button.visibility = View.GONE
            medicine_recycler_view.visibility = View.GONE
            disease_details.visibility = View.VISIBLE
        }
    }

    private fun setRecyclerView() {
        adapter = MedicineRecyclerViewAdapter(this)
        val layoutManger = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        medicine_recycler_view.adapter = adapter
        medicine_recycler_view.layoutManager = layoutManger
    }

    private fun onClickAddAlarm() {
        add_alarm_button.setOnClickListener {
            val intent = AddAlarmActivity.newIntent(activity?.applicationContext)
            startActivityForResult(intent, addAlarmActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == addAlarmActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(EXTRA_MEDICINE_NAME)?.let {
                medicineViewModel.insert(Medicine(it))
            }
        } else {
            Toast.makeText(
                activity?.applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun deleteMedicineAtPosition(medicine: Medicine) {
        medicineViewModel.delete(medicine)
    }

    /*override fun editMedicineAtPosition(medicine: Medicine) {
        val intent = AddAlarmActivity.newIntent(activity?.applicationContext)
        startActivityForResult(intent, addAlarmActivityRequestCode)
    }*/
}


