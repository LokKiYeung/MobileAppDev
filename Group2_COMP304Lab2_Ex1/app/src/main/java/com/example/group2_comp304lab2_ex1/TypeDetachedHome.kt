package com.example.group2_comp304lab2_ex1

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TypeDetachedHome : HomeTypeMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_detached_home)
        val rvDetachedHome = findViewById<RecyclerView>(R.id.rvDetachedHome)

        recyclerViewData = ArrayList()
        recyclerViewData.add(
            HomeRecyclerViewData(
                "1234 Maple Street, Denver, CO 80202",
                1200f, false, "detach1.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "5678 Oak Street, Miami, FL 33101",
                1800f,
                false,
                "detach2.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "2468 Elm Street, New York, NY 10001",
                2500f,
                false,
                "detach3.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "1357 Birch Lane, Chicago, IL 60601",
                3000f,
                false,
                "detach4.png"
            )
        )

        loadData(rvDetachedHome, recyclerViewData)
    }

}