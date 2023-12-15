package at.ac.fhcampuswien.temperatureexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lebensmittel_data")
data class lebensmittelData(
    @PrimaryKey(autoGenerate = true)
    var dataId: Long = 0,
    @ColumnInfo(name = "Lebensmittel")
    var lebensmittel: String,
    @ColumnInfo(name = "Gewicht")
    var gewicht: Long,
    @ColumnInfo(name = "Kohlenhydrate")
    var kohlenhydrate: Long
) {
    val celsiusText: String
    get() {
        return "$gewicht KG."
    }
    val kohlenhydrateText: String
        get() {
            return "$kohlenhydrate kcal."
        }
}
