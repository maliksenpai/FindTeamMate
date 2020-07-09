package com.e.findteammate.Model.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.R

class TalentAdapter(val liste: MutableList<String>): RecyclerView.Adapter<TalentAdapter.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val text = itemview.findViewById<TextView>(R.id.talentlisttext)
        val button = itemview.findViewById<ImageButton>(R.id.talentlistbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }


    override fun onBindViewHolder(holder: TalentAdapter.ViewHolder, position: Int) {
        holder.text.setText(liste[position])
        holder.button.setOnClickListener {
            liste.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun cleardata() {
        liste.clear()
    }
}