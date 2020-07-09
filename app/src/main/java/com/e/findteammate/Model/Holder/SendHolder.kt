package com.e.findteammate.Model.Holder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.Chatui
import com.e.findteammate.Model.Module.ChatModule
import com.e.findteammate.R
import java.util.zip.Inflater

class SendHolder(itemView: View) : MessageHolder<Chatui>(itemView) {

    private var nick:TextView? = null
    private var text:TextView? = null

    init {
        nick = itemView.findViewById(R.id.listsendnick)
        text = itemView.findViewById(R.id.listsendtext)
    }


    override fun bind(item: Chatui) {
        Log.d("gelex5",item.message.text)
        nick?.setText(item.message.nick)
        text?.setText(item.message.text)
    }

}