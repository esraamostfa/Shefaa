package com.azhariangirls.shefaa2.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azhariangirls.shefaa2.R
import com.azhariangirls.shefaa2.model.Medicine
import kotlinx.android.synthetic.main.medicine_list_item_card.view.*

class MedicineRecyclerViewAdapter(private val listener: MedicineRecyclerViewAdapterListener) :
    RecyclerView.Adapter<MedicineRecyclerViewAdapter.ViewHolder>(), ItemTouchHelperListener {

    private var medicines = emptyList<Medicine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.medicine_list_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = medicines.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(medicines[position])
    }

    internal fun updateMedicines(medicines: List<Medicine>) {
        this.medicines = medicines
        notifyDataSetChanged()

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        private lateinit var medicine: Medicine

        fun bind(medicine: Medicine) {
            this.medicine = medicine
            itemView.medicine_name.text = medicine.name
        }

        override fun onClick(v: View?) {
            createDialog(v?.context, adapterPosition)
        }
    }

    override fun createDialog(context: Context?, position: Int) {

        context?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                    listener.deleteMedicineAtPosition(medicines[position])
                    dialog.dismiss()
                }
                /*.setNeutralButton("Edit"){dialog, _ ->
                    listener.editMedicineAtPosition(medicines[position])
                    dialog.dismiss()
                }*/
                .setNegativeButton(R.string.dialog_negative_button) { dialog, _ ->
                    dialog.dismiss()
                }
                .create().show()
        }
    }

    interface MedicineRecyclerViewAdapterListener {
        fun deleteMedicineAtPosition(medicine: Medicine)
        //fun editMedicineAtPosition(medicine: Medicine)
    }

}