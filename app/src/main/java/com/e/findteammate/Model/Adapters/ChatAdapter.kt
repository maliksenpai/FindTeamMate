package com.e.findteammate.Model.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Holder.MessageHolder
import com.e.findteammate.Model.Holder.RecieveHolder
import com.e.findteammate.Model.Holder.SendHolder
import com.e.findteammate.R
import java.lang.IllegalArgumentException

class ChatAdapter(val liste:MutableList<Chatui>): RecyclerView.Adapter<MessageHolder<*>>() {

    override fun getItemViewType(position: Int): Int {
        return liste[position].messagetype
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder<*> {
        val context = parent.context
        return when(viewType){
            0 ->{
                val view = LayoutInflater.from(context).inflate(R.layout.send_message,parent,false)
                SendHolder(view)
            }
            1-> {
                val view = LayoutInflater.from(context).inflate(R.layout.received_message,parent,false)
                RecieveHolder(view)
            }
            else -> throw IllegalArgumentException("hata")
        }
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: MessageHolder<*>, position: Int) {
        val item = liste[position]
        when (holder){
            is SendHolder -> holder.bind(item)
            is RecieveHolder -> holder.bind(item)
        }
    }


}







   /* override fun getItemViewType(position: Int): Int {
        return if(list[position].messagetype == SEND_MESSAGE){
            SEND_MESSAGE
        }else{
            RECIEVE_MESSAGE
        }
    }

    fun cleardata(){
        list.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        Log.d("gelenx4",viewType.toString()+"678")
        if(viewType== 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.send_message,parent,false)
            return SendHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.received_message,parent,false)
            return RecieveHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        Log.d("gelenx5",holder.itemViewType.toString()+"5467")
        Log.d("gelenx8","sdgsdgsd")
        var item = list[position]
        holder.bind(item)
    }
    inner class SendHolder (view: View) : MessageViewHolder(view){
        var nick = view.findViewById<TextView>(R.id.listsendnick)
        var text = view.findViewById<TextView>(R.id.listsendtext)
        override fun bind(message: Chatui) {
            nick.text = message.message.nick
            text.text = message.message.text
        }
    }
    inner class RecieveHolder (view: View) : MessageViewHolder(view){
        var nick = view.findViewById<TextView>(R.id.listalicinick)
        var text = view.findViewById<TextView>(R.id.listalicitext)
        override fun bind(message: Chatui) {
            nick.text = message.message.nick
            text.text = message.message.text
        }
    }

}
open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view){
    open fun bind(message:Chatui){}
}*/
