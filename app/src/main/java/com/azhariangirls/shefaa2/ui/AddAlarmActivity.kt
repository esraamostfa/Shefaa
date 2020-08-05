package com.azhariangirls.shefaa2.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.azhariangirls.shefaa2.R
import com.azhariangirls.shefaa2.viewmodel.MedicineViewModel
import kotlinx.android.synthetic.main.activity_add_alarm.*

class AddAlarmActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context?): Intent {
            val intent = Intent(context, AddAlarmActivity::class.java)
            return intent
        }
    }

    private lateinit var medicineViewModel: MedicineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_alarm)

        medicineViewModel = ViewModelProvider(this).get(MedicineViewModel::class.java)

        setSpinner()
        save_button.setOnClickListener {
            onClickSaveButton()
        }
    }

    private fun setSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.alarm_number, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        times_spinner.adapter = adapter
    }

    private fun onClickSaveButton() {
        val medicineName = medicine_name.text.trim().toString()
        val replyIntent = HomeFragment.newReplyIntent(medicineName)
        if (TextUtils.isEmpty(medicineName)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            setResult(Activity.RESULT_OK, replyIntent)
        }
        this.finish()
    }
}
