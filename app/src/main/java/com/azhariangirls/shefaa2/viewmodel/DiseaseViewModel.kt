package com.azhariangirls.shefaa2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.azhariangirls.shefaa2.model.*


class DiseaseViewModel(application: Application) : AndroidViewModel(application) {

    val diseases: MutableList<Disease> = DiseasesStore.getDiseases()

    init {
        DiseasesStore.loadDiseases(application)
    }

}