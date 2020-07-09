package com.e.findteammate.Model.Holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.ChatAdapter
import com.e.findteammate.Model.Adapters.Chatui
import com.e.findteammate.Model.Module.ChatModule
import com.e.findteammate.R

class RecieveHolder(itemView: View): MessageHolder<Chatui>(itemView){

    private var nick:TextView? = null
    private var text:TextView? = null

    init {
        nick = itemView.findViewById(R.id.listalicinick)
        text = itemView.findViewById(R.id.listalicitext)
    }

    override fun bind(item: Chatui) {
        nick?.setText(item.message.nick)
        text?.setText(item.message.text)
    }
}