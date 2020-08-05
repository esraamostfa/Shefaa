package com.azhariangirls.shefaa2.app

import android.app.Application

class ShefaaApplication : Application() {

    companion object{
        //lateinit var database: MedicineDb
        //lateinit var instance: ShefaaApplication

        //fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        //instance = this
        super.onCreate()

        //database = Room.databaseBuilder(this, MedicineDb::class.java, "medicine_database")
         //   .addCallback(roomDatabaseCallback).build()

        //ShefaaStore.loadDiseases(this)
    }

    /*private val roomDatabaseCallback = object : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }
    }

    private class PopulateDatabaseAsync(db: MedicineDb) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            return null
        }

    }*/
}