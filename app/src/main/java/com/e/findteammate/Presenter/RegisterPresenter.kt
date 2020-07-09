package com.e.findteammate.Presenter

import android.text.TextUtils
import com.e.findteammate.Model.Firebase.Register
import com.e.findteammate.View.Activity.RegisterPage

class RegisterPresenter {
    fun registeruser(mail:String,nick:String,pass:String,registerpage: RegisterPage){
        if(TextUtils.isEmpty(mail) && android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            registerpage.errors(1)
        }else if(nick.length<8){
            registerpage.errors(2)
        }else if(pass.length<8){
            registerpage.errors(3)
        }else{
            Register().registeruser(mail,nick,pass,registerpage)
        }
    }
}