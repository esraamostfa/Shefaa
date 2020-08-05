package com.azhariangirls.shefaa2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.azhariangirls.shefaa2.model.Medicine
import com.azhariangirls.shefaa2.model.MedicineDb
import com.azhariangirls.shefaa2.model.MedicineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MedicineRepository

    // Using LiveData to cash list of medicines has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allMedicines: LiveData<List<Medicine>>

    init {
        /*
    Warning: Don't keep a reference to a context that has a shorter lifecycle than your ViewModel!
    Examples are: Activity, Fragment, View
    Keeping a reference can cause a memory leak, e.g. the ViewModel has a reference to a destroyed Activity!
    All these objects can be destroyed by the operative system and recreated when there's a configuration change,
    and this can happen many times during the lifecycle of a ViewModel.
    If you need the application context (which has a lifecycle that lives as long as the application does), use AndroidViewModel
         */
        val medicineDao = MedicineDb.getDatabase(application, viewModelScope).medicineDao()
        repository = MedicineRepository(medicineDao)
        allMedicines = repository.allMedicines
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(medicine: Medicine) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(medicine)
    }

    fun delete(medicine: Medicine) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(medicine)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}