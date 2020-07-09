package com.e.findteammate.Model.Firebase

import android.content.Intent
import com.e.findteammate.View.Activity.LinkedinPage
import com.e.findteammate.View.Activity.TalentPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Talent {
    fun addtalents(list:MutableList<String>,page: TalentPage){
        var database= FirebaseDatabase.getInstance().reference.child("kullanicilar")
        var mail= FirebaseAuth.getInstance().currentUser?.email
        var listener= object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TalentPage().errors(3)
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("mail").getValue().toString().equals(mail)){
                        var db=it.ref.child("yetenekler")
                        db.setValue(list)
                        page.startActivity(Intent(page.applicationContext, LinkedinPage::class.java))
                    }
                }
            }

        }
        database.addListenerForSingleValueEvent(listener)
    }
}