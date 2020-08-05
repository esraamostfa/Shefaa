package com.azhariangirls.shefaa2.model

import androidx.lifecycle.LiveData

class MedicineRepository(private val medicineDao: MedicineDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allMedicines : LiveData<List<Medicine>> = medicineDao.getAllMedicines()

    //The suspend modifier tells the compiler that this needs to be called from a coroutine
    // or another suspending function.
    suspend fun insert(medicine: Medicine) {
        medicineDao.insert(medicine)
    }

    suspend fun delete(medicine: Medicine) {
        medicineDao.delete(medicine)
    }

    suspend fun deleteAll() {
        medicineDao.deleteAll()
    }

}