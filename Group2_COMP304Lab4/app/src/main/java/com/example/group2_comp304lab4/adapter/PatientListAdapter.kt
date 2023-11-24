package com.example.group2_comp304lab4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.group2_comp304lab4.R
import com.example.group2_comp304lab4.model.Patient

class PatientListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Patient, PatientListAdapter.PatientViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val current = getItem(position)
        // handle on item click event of recycler view
        holder.itemView.setOnClickListener {
            onClickListener.onClick(current)
        }
        // set the text to be displayed in the recycler view
        holder.bind("Patient ${current.patientId} (Name: ${current.firstname} ${current.lastname})")
    }

    class OnClickListener(val clickListener: (patient: Patient) -> Unit) {
        fun onClick(patient: Patient) = clickListener(patient)
    }

    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): PatientViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return PatientViewHolder(view)
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Patient>() {
        override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem.patientId == newItem.patientId
        }

    }

}