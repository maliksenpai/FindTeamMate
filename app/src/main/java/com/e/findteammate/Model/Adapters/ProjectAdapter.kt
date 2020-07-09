package com.e.findteammate.Model.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Module.MainModule
import com.e.findteammate.Model.Module.ProjectModule
import com.e.findteammate.R
import com.e.findteammate.View.Activity.ProjectPage
import com.e.findteammate.View.Activity.SearchProfilePage

class ProjectAdapter(val liste: MutableList<ProjectModule>): RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var nick = itemview.findViewById<TextView>(R.id.projectlistnick)
        var subject = itemview.findViewById<TextView>(R.id.projectlistsubject)
        var layout = itemview.findViewById<LinearLayout>(R.id.projectlistlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item_3, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nick.setText(liste[position].nick)
        holder.subject.setText(liste[position].subject)
        holder.layout.setOnClickListener {
            var intent = Intent(holder.itemView.context,ProjectPage::class.java)
            intent.putExtra("subject",liste[position].subject)
            holder.itemView.context.startActivity(intent)
        }
    }
}
