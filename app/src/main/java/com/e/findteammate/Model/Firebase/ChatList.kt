package com.e.findteammate.Model.Firebase

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.ChatListAdapter
import com.e.findteammate.R
import com.e.findteammate.View.Activity.ChatListPage
import com.e.findteammate.View.Activity.ChatPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatList {
    fun list(page:ChatListPage){
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var mail = FirebaseAuth.getInstance().currentUser?.email
        var list :MutableList<String> = mutableListOf()
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("mail").getValue().toString().equals(mail)){
                        Log.d("gelenchat3",it.child("mesajlar").key.toString())
                        it.child("mesajlar").children.forEach {
                            Log.d("gelenchat2",it.key.toString())
                            list.add(it.key.toString())
                        }
                    }
                }
                var recyclerView = page.findViewById<RecyclerView>(R.id.chatlistrecyclerview)
                recyclerView.layoutManager = LinearLayoutManager(page)
                recyclerView.addItemDecoration(DividerItemDecoration(page, DividerItemDecoration.VERTICAL))
                recyclerView.adapter = ChatListAdapter(list)
            }

        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun startact(recievenick:String,page:Context){
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var mail = FirebaseAuth.getInstance().currentUser?.email
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("mail").getValue().toString().equals(mail)){
                        var intent = Intent(page, ChatPage::class.java)
                        intent.putExtra("recievenick",recievenick)
                        intent.putExtra("sendnick",it.child("nick").getValue().toString())
                        page.startActivity(intent)
                    }
                }
            }

        }
        database.addListenerForSingleValueEvent(listener)
    }
}