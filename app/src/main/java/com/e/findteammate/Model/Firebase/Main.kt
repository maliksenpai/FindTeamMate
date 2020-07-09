package com.e.findteammate.Model.Firebase

import android.app.NotificationManager
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.MainAdapter
import com.e.findteammate.Model.Module.MainModule
import com.e.findteammate.R
import com.e.findteammate.View.Activity.LoginPage
import com.e.findteammate.View.Activity.MainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Main {
    fun checkuser(page: MainPage){
        var mail= FirebaseAuth.getInstance().currentUser?.email
        if(mail.isNullOrEmpty()){
            page.startActivity(Intent(page, LoginPage::class.java))
            Log.d("gelen",mail.toString())
        }
    }
    fun listusers(search:String,page: MainPage){
        var mail= FirebaseAuth.getInstance().currentUser?.email
        Log.d("gelenprof","zxc")
        var i = 0
        var n = 0
        var count = search.length
        var database= FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var recyclerview = page.findViewById<RecyclerView>(R.id.mainpage_list)
        var list:MutableList<MainModule> = mutableListOf()
        var listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                page.errors(1)
            }

            override fun onDataChange(p0: DataSnapshot) {

                p0.children.forEach {
                    Log.d("xgelen8",search)
                    if (search.length != 0) {
                        var talents = it.child("yetenekler").getValue() as MutableList<String>
                        Log.d("xgelen7", talents.toString())
                        for (talent in talents) {
                            i = 0
                            Log.d("xgelen6", talent)
                            n = 0
                           /* while (i < count) {
                                if (search[i].toString() == talent[i].toString()) {
                                    n++
                                }
                                i++
                            }
                            if (i == n && !(it.child("mail").getValue().toString().equals(mail))) {
                                Log.d("xgelen1","eklendi"+talent+"-"+mail)
                                list.add(
                                    MainModule(
                                        it.child("nick").getValue().toString(),
                                        it.child("baglanti").getValue().toString(),
                                        talents[0],
                                        talents[1]
                                    )
                                )
                                break   //eğer özelliklerden biri doğruysa for döngünüsnden çıkması için konuldu hata olursa düzelt
                            } */
                            if(talent.contains(search,ignoreCase = true)){
                                Log.d("xgelen1","eklendi"+talent+"-"+mail)
                                list.add(
                                    MainModule(
                                        it.child("nick").getValue().toString(),
                                        it.child("baglanti").getValue().toString(),
                                        talents[0],
                                        talents[1]
                                    )
                                )
                            }
                        }
                    }
                }
                Log.d("mailgelen",list.toString()+"asd")
                recyclerview.layoutManager = LinearLayoutManager(page)
                recyclerview.addItemDecoration(DividerItemDecoration(page.applicationContext, DividerItemDecoration.VERTICAL))
                recyclerview.adapter = MainAdapter(list)
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun listallusers(page: MainPage){
        var database= FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var recyclerview = page.findViewById<RecyclerView>(R.id.mainpage_list)
        var list:MutableList<MainModule> = mutableListOf()
        val mail = FirebaseAuth.getInstance().currentUser?.email
        val listener = object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if (!it.child("mail").getValue().toString().equals(mail)) {
                        var talents= it.child("yetenekler").getValue() as MutableList<String>
                        list.add(
                            MainModule(
                                it.child("nick").getValue().toString(),
                                it.child("baglanti").getValue().toString(),
                                talents.get(0),
                                talents.get(1)
                            )
                        )
                    }
                }
                Log.d("mailgelen",list.toString()+"asd")
                recyclerview.layoutManager = LinearLayoutManager(page)
                recyclerview.addItemDecoration(DividerItemDecoration(page.applicationContext, DividerItemDecoration.VERTICAL))
                recyclerview.adapter = MainAdapter(list)
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }
    fun getmessages(page:MainPage,manager:NotificationManager){
        var database =  FirebaseDatabase.getInstance().reference.child("kullanicilar")
        val mail = FirebaseAuth.getInstance().currentUser?.email
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("mail").getValue().toString().equals(mail)){
                        Log.d("gelenkey",it.key+"xx")
                        listenermessage(page,it.key.toString(),manager)
                    }
                }
            }

        }
        database.addListenerForSingleValueEvent(listener)
    }
    fun listenermessage(page:MainPage,key:String,manager:NotificationManager) {
        var database =  FirebaseDatabase.getInstance().reference.child("kullanicilar").child(key).child("mesajlar")
        val mail = FirebaseAuth.getInstance().currentUser?.email
        val listener = object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("gelenasd","asdasd")
                MainPage().createnotification(page,manager)
            }
        }
        database.addValueEventListener(listener)
    }
}