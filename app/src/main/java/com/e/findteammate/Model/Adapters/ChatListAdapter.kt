package com.e.findteammate.Model.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Firebase.ChatList
import com.e.findteammate.Model.Module.MainModule
import com.e.findteammate.R
import com.e.findteammate.View.Activity.ChatPage
import com.e.findteammate.View.Activity.SearchProfilePage

class ChatListAdapter (val liste: MutableList<String>): RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var nick = itemview.findViewById<TextView>(R.id.chatlisttext)
        var layout = itemview.findViewById<LinearLayout>(R.id.chatlistlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_4, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nick.setText(liste[position])
        holder.layout.setOnClickListener {
            ChatList().startact(liste[position],holder.itemView.context)
        }
    }

    fun cleardata() {
        liste.clear()
    }
}