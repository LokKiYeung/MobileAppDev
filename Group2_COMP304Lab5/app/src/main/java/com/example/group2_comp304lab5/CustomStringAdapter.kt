package com.example.group2_comp304lab5

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomStringAdapter(
    private val stringItems: Array<String>,
    private val supplimentaryItems: Array<String>? = null,
    private val onClickListener: OnItemClickListener
) : RecyclerView.Adapter<CustomStringAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.simple_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // get the item
        val currentItem = stringItems[position]
        var supplementaryItem = if (supplimentaryItems == null) "" else supplimentaryItems[position]
        val textView: TextView = viewHolder.itemView.findViewById(R.id.text_item)

        if (supplementaryItem != "") {
            textView.textSize = 20f
            textView.text = Html.fromHtml("<b>${currentItem}</b><br>${supplementaryItem}")
        } else {
            textView.text = currentItem
        }

        // set onclick listener
        viewHolder.itemView.setOnClickListener {
            onClickListener.onItemClick(currentItem)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = stringItems.size

    class OnItemClickListener(val clickListener: (item: String) -> Unit) {
        fun onItemClick(item: String) = clickListener(item)
    }
}