package com.e.findteammate.Presenter

import android.util.Log
import com.e.findteammate.Model.Firebase.Main
import com.e.findteammate.View.Activity.MainPage

class MainPresenter {
    fun checktext(search:String,page: MainPage){
        if(search!=""){
            Main().listusers(search,page)
            Log.d("gelen8","oldu")
        }else{
            Main().listallusers(page)
            Log.d("xgelen2","bos")
        }
    }
}