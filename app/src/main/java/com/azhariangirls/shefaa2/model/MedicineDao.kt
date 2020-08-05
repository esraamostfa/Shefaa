package com.azhariangirls.shefaa2.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicineDao {

    @Insert
    //The suspend modifier tells the compiler that this needs to be called from a coroutine
    // or another suspending function.
    suspend fun insert(medicine: Medicine)

    @Delete
    suspend fun delete(medicine: Medicine)

    @Query("DELETE FROM medicine_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM medicine_table")
    fun getAllMedicines() : LiveData<List<Medicine>>
}