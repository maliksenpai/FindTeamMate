package com.e.findteammate.Model.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.Chatui

abstract class MessageHolder<in T>(itemView: View):RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Chatui)
}