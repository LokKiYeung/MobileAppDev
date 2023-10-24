package com.example.group2_comp304lab2_ex1

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TypeApartment : HomeTypeMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_apartment)
        val rvApartment = findViewById<RecyclerView>(R.id.rvApartment)

        recyclerViewData = ArrayList()
        recyclerViewData.add(
            HomeRecyclerViewData(
                "123 Elm Street, Apartment 4B, Springfield, IL 62701",
                1200f, false, "apartment1.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "456 Pine Avenue, Unit 210, Portland, OR 97204",
                1800f,
                false,
                "apartment2.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "789 Oak Lane, Suite 601, Austin, TX 78701",
                2200f,
                false,
                "apartment3.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "101 Maple Road, Apt 3C, New York, NY 10001",
                2300f,
                false,
                "apartment4.png"
            )
        )

        loadData(rvApartment, recyclerViewData)
    }

}