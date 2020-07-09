package com.e.findteammate.Presenter

import android.widget.Button
import com.e.findteammate.Model.Firebase.SearchProfile
import com.e.findteammate.R
import com.e.findteammate.View.Activity.SearchProfilePage

class SearchProfilePresenter {
    fun votebutton(nick:String,page:SearchProfilePage){
        val button = page.findViewById<Button>(R.id.searchvote)
        if(button.text.toString() == "Suggest"){
            SearchProfile().vote(nick,page)
        }
        else{
            SearchProfile().unvote(nick,page)
        }
    }
}