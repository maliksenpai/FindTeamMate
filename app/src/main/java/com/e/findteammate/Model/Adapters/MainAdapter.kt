package com.e.findteammate.Model.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Module.MainModule
import com.e.findteammate.R
import com.e.findteammate.View.Activity.SearchProfilePage

class MainAdapter(val liste: MutableList<MainModule>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var nick = itemview.findViewById<TextView>(R.id.mainlistnick)
        var connection = itemview.findViewById<TextView>(R.id.mainlistconnection)
        var talent1 = itemview.findViewById<TextView>(R.id.mainlisttalent1)
        var talent2 = itemview.findViewById<TextView>(R.id.mainlisttalent2)
        var layout = itemview.findViewById<RelativeLayout>(R.id.mainlistlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }


    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.nick.setText(liste[position].nick)
        holder.connection.setText(liste[position].connection)
        holder.talent1.setText(liste[position].talent1)
        holder.talent2.setText(liste[position].talent2)
        holder.layout.setOnClickListener {
            var intent= Intent(holder.itemView.context,
                SearchProfilePage::class.java)
            intent.putExtra("nick",liste[position].nick)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun cleardata() {
        liste.clear()
    }
}