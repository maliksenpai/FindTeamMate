package com.e.findteammate.Model.Firebase

import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.ProfileAdapter
import com.e.findteammate.R
import com.e.findteammate.View.Activity.LoginPage
import com.e.findteammate.View.Activity.MainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Profile{

    fun getDetail(page: MainPage) {
        var mail = FirebaseAuth.getInstance().currentUser?.email
        Log.d("gelenprof", "zxc")
        Log.d("gelenprof", mail + "xasd")
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var recyclerview = page.findViewById<RecyclerView>(R.id.profiletalentlist)
        var listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //ProfilePage().errors(1)
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.d("gelenprof1", it.child("mail").getValue().toString())
                    if (it.child("mail").getValue().toString().equals(mail)) {
                        Log.d("gelenprof2", it.child("baglanti").getValue().toString())
                        page.findViewById<TextView>(R.id.profileconnectioncount)
                            .setText(it.child("baglanti").getValue().toString())
                        var list: MutableList<String> =
                            it.child("yetenekler").getValue() as MutableList<String>
                        recyclerview.adapter = ProfileAdapter(list)
                        if (it.child("linkedin").getValue().toString().length!=4) {
                            page.findViewById<TextView>(R.id.profilecontact).setText(
                                "Mail:" + mail + "\n Linkedin:" + it.child("linkedin").getValue()
                                    .toString()
                            )
                        } else {
                            page.findViewById<TextView>(R.id.profilecontact).setText("Mail:" + mail)
                        }
                        Log.d("profilegelen",it.child("linkedin").getValue().toString().isNullOrBlank().toString())
                        if(it.child("bilgi").getValue().toString().length!=4){
                            page.findViewById<TextView>(R.id.profileinfo).setText("Hakkımda \n"+it.child("bilgi").getValue().toString())
                        }else{
                            page.findViewById<TextView>(R.id.profileinfo).setText("")
                        }
                        // Log.d("gelen",list.toString()+"zddc")
                    }
                }
            }

        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun profilebar(page:MainPage){
        var mail = FirebaseAuth.getInstance().currentUser?.email
        var database = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("mail").getValue().toString().equals(mail)){
                        page.supportActionBar?.title=it.child("nick").getValue().toString()
                    }
                }
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun exituser(page: MainPage){
        var user = FirebaseAuth.getInstance()
        user.signOut()
        page.startActivity(Intent(page,
            LoginPage::class.java))
    }

}