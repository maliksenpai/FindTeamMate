package com.e.findteammate.Model.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.R

class ProfileAdapter(val liste: MutableList<String>): RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val text = itemview.findViewById<TextView>(R.id.profiletalentlisttext)
        val layout = itemview.findViewById<LinearLayout>(R.id.profilelistlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        holder.text.setText(liste[position])
        if(position%2==0){
            holder.layout.setBackgroundColor(Color.parseColor("#03DAC5"))
        }
    }

    fun cleardata() {
        liste.clear()
    }
}