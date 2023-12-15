package APP.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface lebensmittelDao {

    @Insert
    fun insert(data: lebensmittelData)

    @Update
    fun update(data: lebensmittelData)

    @Query("SELECT * FROM lebensmittel_data WHERE dataId = :key")
    fun getById(key: Long): lebensmittelData?

    @Query("SELECT * FROM lebensmittel_data WHERE lebensmittel = :lebensmittel")
    fun getByLebensmittel(lebensmittel: String): lebensmittelData?

    @Query("SELECT * FROM lebensmittel_data")
    fun getAllLocations(): List<lebensmittelData>

    @Query("DELETE from lebensmittel_data WHERE dataId= :key")
    fun delete(key:Long): Int

    @Query("DELETE FROM lebensmittel_data")
    fun deleteAll(): Void
}
