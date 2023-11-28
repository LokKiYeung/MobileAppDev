package com.example.group2_comp304lab5


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val EXTRA_LANDMARK_TYPE = "com.example.group2_comp304lab5.LANDMARK_TYPE"
const val EXTRA_LANDMARK = "com.example.group2_comp304lab5.LANDMARK"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val landmarkTypes = resources.getStringArray(R.array.landmarkTypeArray)
        val rvLandmarkType = findViewById<RecyclerView>(R.id.rvLandmarkType)
        val landmarkTypesAdapter =
            CustomStringAdapter(landmarkTypes, null, CustomStringAdapter.OnItemClickListener() {
                onItemClick(it)
            })
        rvLandmarkType.adapter = landmarkTypesAdapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvLandmarkType.layoutManager = layoutManager

    }

    private fun onItemClick(item: String) {
        val intent = Intent(this, LandmarkListActivity::class.java)
        intent.putExtra(EXTRA_LANDMARK_TYPE, item)
        startActivity(intent)
    }
}