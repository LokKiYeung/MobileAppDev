package com.example.group2_comp304lab2_ex1

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TypeTownHouse : HomeTypeMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_town_house)
        val rvTownHouse = findViewById<RecyclerView>(R.id.rvTownHouse)

        recyclerViewData = ArrayList()
        recyclerViewData.add(
            HomeRecyclerViewData(
                "1234 Maple Avenue, Denver, CO 80202",
                2500f, false, "condo1.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "2456 Oak Street, Miami, FL 33101",
                2500f,
                false,
                "condo2.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "9876 Pine Road, Los Angeles, CA 90001",
                2700f,
                false,
                "condo3.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "2468 Elm Lane, New York, NY 10001",
                3000f,
                false,
                "condo4.png"
            )
        )

        loadData(rvTownHouse, recyclerViewData)
    }

}