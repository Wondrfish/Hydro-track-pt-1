package com.example.hydrotrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var db: WaterDatabase
    private lateinit var dao: WaterDao
    private lateinit var adapter: WaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, WaterDatabase::class.java, "water_db").build()
        dao = db.waterDao()

        adapter = WaterAdapter(listOf())
        val recycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.waterRecycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        val glassesInput = findViewById<android.widget.EditText>(R.id.glassesInput)
        val addButton = findViewById<android.widget.Button>(R.id.addButton)

        addButton.setOnClickListener {
            val glasses = glassesInput.text.toString().toIntOrNull() ?: 0
            val date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())
            lifecycleScope.launch {
                dao.insert(WaterEntry(date = date, glasses = glasses))
            }
            glassesInput.text.clear()
        }

        lifecycleScope.launch {
            dao.getAllEntries().collect { entries ->
                adapter.updateData(entries)
                if (entries.isNotEmpty()) {
                    val avg = entries.map { it.glasses }.average()
                    findViewById<TextView>(R.id.averageText).text =
                        "Average: %.1f glasses/day".format(avg)
                }
            }
        }
        }
    }
