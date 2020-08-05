package com.azhariangirls.shefaa2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Medicine::class],
    version = 1
)
abstract class MedicineDb : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: MedicineDb? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MedicineDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicineDb::class.java,
                    "medicine_database"
                ).addCallback(DatabaseCallback(scope, context))
                    .build()
                return instance
            }
        }

    }

    /*
    To delete all content and repopulate the database whenever the app is started,
     you create a RoomDatabase.Callback and override onOpen().
     Because you cannot do Room database operations on the UI thread,
     onOpen() launches a coroutine on the IO Dispatcher.
     */

    class DatabaseCallback(private val scope: CoroutineScope, private val context: Context) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.medicineDao())
                }
            }
        }

        suspend fun populateDatabase(medicineDao: MedicineDao) {

            // Delete all content here.
            medicineDao.deleteAll()

        }
    }
}
