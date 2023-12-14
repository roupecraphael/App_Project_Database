package at.ac.fhcampuswien.temperatureexample


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.ac.fhcampuswien.temperatureexample.data.LebensmittelData
import at.ac.fhcampuswien.temperatureexample.data.LebensmittelDatabase
import at.ac.fhcampuswien.temperatureexample.databinding.ActivityMainBinding
import at.ac.fhcampuswien.temperatureexample.databinding.ActivitySecondBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var locations = listOf<LebensmittelData>()
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val LebensmittelView: EditText = findViewById(R.id.lebensmittel)
        val GewichtView: EditText = findViewById(R.id.gewicht)
        //val button: Button = findViewById(R.id.adddata)
        val db = LebensmittelDatabase.getInstance(applicationContext)
        val dao = db.temperatureDao
        locations = dao.getAllLocations()
        binding.temp = locations[0]

        val goToAnotherClass: Button
        goToAnotherClass =
            findViewById<View>(R.id.showdata) as Button //find the button by its assigned id
        goToAnotherClass.setOnClickListener { // TODO Auto-generated method stub
            val myIntent = Intent(
                this,
                SecondActivity::class.java
            )
            startActivity(myIntent)
        }

        val addDatatodatabase: Button
        addDatatodatabase = findViewById(R.id.adddata) as Button

        addDatatodatabase.setOnClickListener {
            val lebensmitteldaten = LebensmittelView.text.toString()
            val gewichtdaten = GewichtView.text.toString().toLong()
            val tempData =
                LebensmittelData(lebensmittel = lebensmitteldaten, gewicht = gewichtdaten)
            dao.insert(tempData)
        }
    }
}

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        val view: RecyclerView = findViewById(R.id.LebensmittelListe)
        val db = LebensmittelDatabase.getInstance(applicationContext)
        val dao = db.temperatureDao
        val adapter = LebensmittelAdapter(dao.getAllLocations())
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(this)
    }
}

class LebensmittelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val lebensmittelName: TextView = itemView.findViewById(R.id.lebensmittelid)
    val gewicht: TextView = itemView.findViewById(R.id.gewichtid)
}

class LebensmittelAdapter(private val lebensmittelList: List<LebensmittelData>) :
    RecyclerView.Adapter<LebensmittelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LebensmittelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_database, parent, false)
        return LebensmittelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LebensmittelViewHolder, position: Int) {
        val lebensmittel = lebensmittelList[position]
        holder.lebensmittelName.text = lebensmittel.lebensmittel
        holder.gewicht.text = lebensmittel.gewicht.toString()
    }

    override fun getItemCount() = lebensmittelList.size
}




