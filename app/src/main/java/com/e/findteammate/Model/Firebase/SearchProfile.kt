package com.e.findteammate.Model.Firebase

import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.ProfileAdapter
import com.e.findteammate.R
import com.e.findteammate.View.Activity.ChatPage
import com.e.findteammate.View.Activity.SearchProfilePage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchProfile {
    fun getDetails(nick:String,page: SearchProfilePage){
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var recyclerview = page.findViewById<RecyclerView>(R.id.searchtalentlist)
        var mymail = FirebaseAuth.getInstance().currentUser?.email
        var list : MutableList<String> = arrayListOf()
        var listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(nick)){
                        Log.d("gelenprof2",it.child("baglanti").getValue().toString())
                        var mail = it.child("mail").getValue().toString()
                        var count = it.child("ovgu").childrenCount.toString()
                        page.findViewById<TextView>(R.id.searchconnectioncount).setText(count)
                        Log.d("gelenprofil2",it.child("ovgu").childrenCount.toString())
                        list = it.child("yetenekler").getValue() as MutableList<String>
                        if(!it.child("linkedin").getValue().toString().isNullOrEmpty()){
                            page.findViewById<TextView>(R.id.searchcontact).setText(
                                "Mail:" + mail + "\n Linkedin:" + it.child("linkedin").getValue()
                                    .toString()
                            )
                        } else {
                            page.findViewById<TextView>(R.id.searchcontact).setText("Mail:" + mail)
                        }
                        if(!it.child("info").getValue().toString().isNullOrEmpty()){
                            page.findViewById<TextView>(R.id.searchinfo).setText("About: \n"+it.child("bilgi").getValue().toString())
                        }
                        var suggest = database.child(it.key.toString()).child("ovgu")
                        var suggestlistener = object : ValueEventListener{
                            override fun onCancelled(p0: DatabaseError) {

                            }

                            override fun onDataChange(p1: DataSnapshot) {
                                p1.children.forEach {
                                    if(mymail.equals(it.getValue().toString())){
                                        Log.d("gelenprofil","oldu")
                                        page.findViewById<Button>(R.id.searchvote).setText("Suggested")
                                    }
                                    else{
                                        Log.d("gelenprofil","olmadı")
                                        page.findViewById<Button>(R.id.searchvote).setText("Suggest")
                                    }
                                }
                            }

                        }
                        suggest.addListenerForSingleValueEvent(suggestlistener)
                    }
                }
                var manager = LinearLayoutManager(page)
                recyclerview.layoutManager=manager
                recyclerview.adapter= ProfileAdapter(list)
            }

        }
        database.addListenerForSingleValueEvent(listener)
    }
    fun message(recievenick:String,page: SearchProfilePage){
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var mail = FirebaseAuth.getInstance().currentUser?.email
        var listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                SearchProfilePage().errors(2)
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.d("gelen","zxczxc")
                    if(it.child("mail").getValue().toString().equals(mail)){
                        var sendnick=it.child("nick").getValue().toString()
                        var intent= Intent(page,
                            ChatPage::class.java)
                        intent.putExtra("recievenick",recievenick)
                        intent.putExtra("sendnick",sendnick)
                        Log.d("gelen","cxv")
                        page.startActivity(intent)
                    }
                }
            }

        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun vote(nick:String,page:SearchProfilePage){
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var mail = FirebaseAuth.getInstance().currentUser?.email
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(nick)){
                        database.child(it.key.toString()).child("ovgu").push().setValue(mail)
                        page.findViewById<Button>(R.id.searchvote).setText("Suggested")
                    }
                }
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun unvote(nick: String,page:SearchProfilePage){
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var mail = FirebaseAuth.getInstance().currentUser?.email
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(nick)){
                        it.child("ovgu").children.forEach {
                            if(it.getValue().toString().equals(mail)){
                                it.ref.removeValue()
                                page.findViewById<Button>(R.id.searchvote).setText("Suggest")
                            }
                        }
                    }
                }
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }
}