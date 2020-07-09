package com.e.findteammate.Model.Firebase

import android.content.Intent
import android.util.Log
import com.e.findteammate.View.Activity.RegisterPage
import com.e.findteammate.View.Activity.TalentPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Register {
    fun registeruser(mail:String,nick:String,pass:String,registerPage: RegisterPage){
        var auth= FirebaseAuth.getInstance()
        var database= FirebaseDatabase.getInstance().reference.child("kullanicilar").push()
        auth.createUserWithEmailAndPassword(mail,pass)
            .addOnCompleteListener {
                Log.d("gelenhata",it.isSuccessful.toString())
                if(it.isSuccessful==true){
                    addaccount(nick,pass,mail)
                    var intent= Intent(registerPage, TalentPage::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    registerPage.startActivity(intent)
                }else{
                    registerPage.errors(4)
                }
            }
    }
    fun addaccount(nick:String,pass:String,mail:String){
        var database= FirebaseDatabase.getInstance().reference.child("kullanicilar").push()
        database.child("kullanıcılar").push()
        database.child("nick").setValue(nick)
        database.child("mail").setValue(mail)
        database.child("baglanti").setValue("0")
    }
}