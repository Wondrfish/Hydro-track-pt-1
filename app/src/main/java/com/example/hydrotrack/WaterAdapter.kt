package com.example.hydrotrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WaterAdapter(private var entries: List<WaterEntry>) :
    RecyclerView.Adapter<WaterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateView: TextView = itemView.findViewById(R.id.dateText)
        val glassesView: TextView = itemView.findViewById(R.id.glassesText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_water, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.dateView.text = entry.date
        holder.glassesView.text = "${entry.glasses} glasses"
    }

    override fun getItemCount() = entries.size

    fun updateData(newEntries: List<WaterEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
}
