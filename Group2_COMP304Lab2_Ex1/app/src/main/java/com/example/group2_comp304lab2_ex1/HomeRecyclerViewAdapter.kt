package com.example.group2_comp304lab2_ex1

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream


class HomeRecyclerViewAdapter(
    private val items: List<HomeRecyclerViewData>,
    private val assetManager: AssetManager,
    private val checked_items: ArrayList<String>
) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewDataHolder>() {
    inner class HomeRecyclerViewDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewDataHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_type_row_layout, parent, false)
        return HomeRecyclerViewDataHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewDataHolder, position: Int) {

        // get the item
        val currentItem: HomeRecyclerViewData = items[position]

        // set the checkbox text and event
        val chxHome: CheckBox = holder.itemView.findViewById(R.id.chxHome)
        chxHome.text = (currentItem.address + ": $" + currentItem.price.toString())
        chxHome.setOnCheckedChangeListener { _, isChecked ->
            currentItem.isChecked = isChecked
        }

        chxHome.isChecked = (currentItem.address in checked_items)

        // set the image
        val tvImage: ImageView = holder.itemView.findViewById(R.id.ivHome)
        val inputStream: InputStream = assetManager.open(currentItem.image_path)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        tvImage.setImageBitmap(bitmap)
        inputStream.close()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}