package at.ac.fhcampuswien.temperatureexample.data
// import all necessary external libraries here --------------------------------------------------------------------------------//
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
// @Dao annotation marks the class as a Data Access Object (DAO) for the Room database with a table name of lebensmittel_data --//
@Dao
interface LebensmittelDao {
// the function insert is used to insert data into the database ----------------------------------------------------------------//
    @Insert
    fun insert(data: LebensmittelData)
// the function update is used to update data in the database ------------------------------------------------------------------//
    @Update
    fun update(data: LebensmittelData)
// the function getById is used to get data from the database by its id ---------------------------------------------------------//
    @Query("SELECT * FROM lebensmittel_data WHERE dataId = :key")
    fun getById(key: Long): LebensmittelData?
// the function getByLebensmittel is used to get data from the database by its lebensmittel ------------------------------------//
    @Query("SELECT * FROM lebensmittel_data WHERE lebensmittel = :lebensmittel")
    fun getByLebensmittel(lebensmittel: String): LebensmittelData?
// the function getAllLocations is used to get all data from the database -------------------------------------------------------//
    @Query("SELECT * FROM lebensmittel_data")
    fun getAllLocations(): List<LebensmittelData>
// the function delete is used to delete data from the database by its id -------------------------------------------------------//
    @Query("DELETE from lebensmittel_data WHERE dataId= :key")
    fun delete(key:Long): Int
// the function deleteAll is used to delete all data from the database ----------------------------------------------------------//
    @Query("DELETE FROM lebensmittel_data")
    fun deleteAll(): Void
}
