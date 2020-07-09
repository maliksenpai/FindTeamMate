package com.e.findteammate.Model.Firebase

import android.content.Intent
import com.e.findteammate.View.Activity.LoginPage
import com.e.findteammate.View.Activity.MainPage
import com.google.firebase.auth.FirebaseAuth

class Login {
    fun login(mail:String,pass:String,page: LoginPage){
        var auth= FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(mail,pass)
            .addOnCompleteListener {
                if(it.isSuccessful==true){
                    var intent= Intent(page,
                        MainPage::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    page.startActivity(intent)
                }else{
                    page.errors(3)
                }
        }
    }
}