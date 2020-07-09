package com.e.findteammate.Model.Firebase

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.ChatAdapter
import com.e.findteammate.Model.Adapters.Chatui
import com.e.findteammate.Model.Module.ChatModule
import com.e.findteammate.R
import com.e.findteammate.View.Activity.ChatPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Chat {
    fun sendmessage(page: ChatPage, message:ChatModule, nick:String){
        val database=FirebaseDatabase.getInstance().reference.child("kullanicilar")
        val mail=FirebaseAuth.getInstance().currentUser?.email
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                p0.children.forEach {
                    if(it.child("mail").getValue().toString().equals(mail)){
                        var db=it.ref.child("mesajlar").child(message.nick).push()
                        db.child("mesaj").setValue(message.text)
                        db.child("gonderen").setValue(nick)
                    }
                    if(it.child("nick").getValue().toString().equals(message.nick)){
                        var db=it.ref.child("mesajlar").child(nick).push()
                        db.child("mesaj").setValue(message.text)
                        db.child("gonderen").setValue(nick)
                    }
                }
                //update(page,nick,message.nick)
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }
    fun update(page: ChatPage, recievenick:String, sendnick:String){
        var list:MutableList<Chatui> = mutableListOf()
        val firebase = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        val recyclerview = page.findViewById<RecyclerView>(R.id.chatlist)
        //val listView = page.findViewById<ListView>(R.id.chatlist)
        //ChatAdapter(list).cleardata()
        Log.d("gelenx3",sendnick)
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(sendnick)){
                        Log.d("gelenx1",it.child("mesajlar").getValue().toString())
                        it.child("mesajlar").child(recievenick).children.forEach {
                            if(it.child("gonderen").getValue().toString().equals(sendnick)){
                                list.add(Chatui(ChatModule(sendnick,it.child("mesaj").getValue().toString()),0))
                            }else{
                                list.add(Chatui(ChatModule(recievenick,it.child("mesaj").getValue().toString()),1))
                            }
                        }
                    }
                }
                val manager = LinearLayoutManager(page)
                manager.stackFromEnd = true
                Log.d("cgelen","yeni")
                recyclerview.layoutManager = manager
                recyclerview.addItemDecoration(DividerItemDecoration(page.applicationContext, DividerItemDecoration.VERTICAL))
                recyclerview.setHasFixedSize(true)
                var adapter = ChatAdapter(list)
                recyclerview.adapter=adapter
                for(a in list){
                    Log.d("ygelen",a.messagetype.toString()+"-"+a.message.text+"asd")
                }
            }

        }
        firebase.addValueEventListener(listener)
    }
}