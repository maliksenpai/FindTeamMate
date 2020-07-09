package com.e.findteammate.Model.Firebase

import android.content.Intent
import com.e.findteammate.View.Activity.LinkedinPage
import com.e.findteammate.View.Activity.MainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Linkedin {
    fun addaccount(page: LinkedinPage, linkedin:String,info:String){
        val firebase = FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var mail = FirebaseAuth.getInstance().currentUser?.email
        var listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                LinkedinPage().errors(0)
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("mail").getValue().toString().equals(mail)){
                        var db = it.ref.child("linkedin").setValue(linkedin)
                        var db2 = it.ref.child("bilgi").setValue(info)
                        page.startActivity(Intent(page,MainPage::class.java))
                    }
                }
            }
        }
        firebase.addListenerForSingleValueEvent(listener)
    }
}