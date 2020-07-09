package com.e.findteammate.Presenter

import android.text.TextUtils
import com.e.findteammate.Model.Firebase.Login
import com.e.findteammate.View.Activity.LoginPage

class LoginPresenter {
    fun checklogin(mail:String,pass:String,login: LoginPage){
        if(TextUtils.isEmpty(mail) && android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            login.errors(1)
        }else if(pass.length<8){
            login.errors(2)
        }else{
            Login().login(mail,pass,login)
        }
    }
}