package com.e.findteammate.Presenter

import android.util.Log
import com.e.findteammate.Model.Firebase.Talent
import com.e.findteammate.View.Activity.TalentPage

class TalentPresenter {
    fun addTalent(talent:String, page: TalentPage, list:MutableList<String>):MutableList<String>{
        if(talent.length<1){
            page.errors(1)
            return list
        }else{
            list.add(talent)
            Log.d("gelen",list.toString())
            return list
        }
    }
    fun sendTalent(list:MutableList<String>,page: TalentPage){
        Log.d("ygelen",list.size.toString())
        if(list.size<3){
            page.errors(2)
        }else{
            Talent().addtalents(list,page)
        }
    }
}