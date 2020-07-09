package com.e.findteammate.Presenter

import com.e.findteammate.Model.Firebase.Linkedin
import com.e.findteammate.View.Activity.LinkedinPage

class LinkedinPresenter {
    fun checktext(page: LinkedinPage, linkedin:String,info : String){
        if(linkedin.length==0){
            LinkedinPage().errors(1)
        }else if(info.length==0){
            LinkedinPage().errors(2)
        }
        else{
            Linkedin().addaccount(page,linkedin,info)
        }
    }
}