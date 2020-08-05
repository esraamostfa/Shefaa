package com.azhariangirls.shefaa2.model

import android.content.Context
import android.widget.Toast
import com.azhariangirls.shefaa2.R
import com.azhariangirls.shefaa2.app.ShefaaApplication
import com.azhariangirls.shefaa2.viewmodel.MedicineViewModel

object DiseasesStore {

    private val diseases = mutableListOf<Disease>()

    fun loadDiseases(context: Context) {
        val diseasesNamesArray = context.resources.getStringArray(R.array.diseases_titles_array)
        val diseasesImagesArray = context.resources.getStringArray(R.array.diseases_images_array)

        for (index in 1..diseasesImagesArray.size) {
            diseases.add(
                Disease(
                    index,
                    diseasesNamesArray[index - 1],
                    diseasesImagesArray[index - 1],
                    ""
                )
            )
        }
    }

    fun getDiseases() = diseases

    fun deleteAll() {
        diseases.forEach { diseases.removeAt(diseases.indexOf(it)) }
    }

}
