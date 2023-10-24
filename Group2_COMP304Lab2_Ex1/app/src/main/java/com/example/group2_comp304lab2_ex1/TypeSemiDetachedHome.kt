package com.example.group2_comp304lab2_ex1

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TypeSemiDetachedHome : HomeTypeMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_semi_detached_home)
        val rvSemiDetachedHome = findViewById<RecyclerView>(R.id.rvSemiDetachedHome)

        recyclerViewData = ArrayList()
        recyclerViewData.add(
            HomeRecyclerViewData(
                "1234 Cedar Street, Denver, CO 80202",
                2600f, false, "semi-detach1.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "5678 Willow Avenue, Miami, FL 33101",
                2800f,
                false,
                "semi-detach2.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "9876 Birch Road, Los Angeles, CA 90001",
                2900f,
                false,
                "semi-detach3.png"
            )
        )
        recyclerViewData.add(
            HomeRecyclerViewData(
                "1111 Elm Road, New York, NY 10001",
                3100f,
                false,
                "semi-detach4.png"
            )
        )

        loadData(rvSemiDetachedHome, recyclerViewData)

    }


}
