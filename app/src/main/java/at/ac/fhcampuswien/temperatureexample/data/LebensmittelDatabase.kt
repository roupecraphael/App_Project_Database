package at.ac.fhcampuswien.temperatureexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LebensmittelData::class], version = 1, exportSchema = false)
abstract class LebensmittelDatabase : RoomDatabase() {

    abstract val temperatureDao: LebensmittelDao

    companion object {
        @Volatile
        private var INSTANCE: LebensmittelDatabase? = null

        fun getInstance(context: Context): LebensmittelDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LebensmittelDatabase::class.java,
                        "lebensmittel_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}