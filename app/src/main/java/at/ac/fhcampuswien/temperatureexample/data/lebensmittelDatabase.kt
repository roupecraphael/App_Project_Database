package at.ac.fhcampuswien.temperatureexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [lebensmittelData::class], version = 1, exportSchema = false)
abstract class lebensmittelDatabase : RoomDatabase() {

    abstract val temperatureDao: lebensmittelDao

    companion object {
        @Volatile
        private var INSTANCE: lebensmittelDatabase? = null

        fun getInstance(context: Context): lebensmittelDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        lebensmittelDatabase::class.java,
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