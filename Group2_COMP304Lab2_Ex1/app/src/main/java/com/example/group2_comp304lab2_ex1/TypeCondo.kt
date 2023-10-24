package com.example.group2_comp304lab2_ex1

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TypeCondo : HomeTypeMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_condo)
        val rvCondo = findViewById<RecyclerView>(R.id.rvCondo)

        recyclerViewData = ArrayList()
        recyclerViewData.add(
            HomeRecyclerViewData(
                "1234 Oak Street, Unit 501, Denver, CO 80202",
                1200f, false, "condo1.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "5678 Pine Avenue, Suite 302, Miami, FL 33101",
                1800f,
                false,
                "condo2.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "9876 Maple Lane, Condo 101, Los Angeles, CA 90001",
                2500f,
                false,
                "condo3.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "2468 Elm Road, Apt 401, New York, NY 10001",
                2600f,
                false,
                "condo4.png"
            )
        )

        loadData(rvCondo, recyclerViewData)
    }

}