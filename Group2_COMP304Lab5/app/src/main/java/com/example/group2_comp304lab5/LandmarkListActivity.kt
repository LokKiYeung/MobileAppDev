package com.example.group2_comp304lab5

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class LandmarkListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_list)

        // get the landmark type
        val landmarkType = intent.getStringExtra(EXTRA_LANDMARK_TYPE)

        var landmarks: Array<String>? = null
        var landmarkAddresses: Array<String>? = null

        // change the title to corresponding landmark type
        var tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = landmarkType

        // show the corresponding buildings
        when (landmarkType) {
            "Old Buildings" -> {
                landmarks = resources.getStringArray(R.array.OldBuildings)
                landmarkAddresses = resources.getStringArray(R.array.OldBuildingsAddress)
            }
            "Museums" -> {
                landmarks = resources.getStringArray(R.array.Museums)
                landmarkAddresses = resources.getStringArray(R.array.MuseumsAddress)
            }
            "Stadiums" -> {
                landmarks = resources.getStringArray(R.array.Stadiums)
                landmarkAddresses = resources.getStringArray(R.array.StadiumsAddress)
            }
            "Attractions" -> {
                landmarks = resources.getStringArray(R.array.Attractions)
                landmarkAddresses = resources.getStringArray(R.array.AttractionsAddress)
            }
        }

        if (landmarks != null) {
            val rvLandmarks = findViewById<RecyclerView>(R.id.rvLandmarks)
            val landmarksAdapter =
                CustomStringAdapter(
                    landmarks,
                    landmarkAddresses,
                    CustomStringAdapter.OnItemClickListener {
                        onItemClick(it)
                    })
            rvLandmarks.adapter = landmarksAdapter
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvLandmarks.layoutManager = layoutManager
        }
    }

    private fun onItemClick(item: String) {
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra(EXTRA_LANDMARK, item)
        startActivity(intent)
    }
}