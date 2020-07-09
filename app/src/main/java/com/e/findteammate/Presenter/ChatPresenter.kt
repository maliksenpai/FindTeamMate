package com.e.findteammate.Presenter

import com.e.findteammate.Model.Firebase.Chat
import com.e.findteammate.Model.Module.ChatModule
import com.e.findteammate.View.Activity.ChatPage

class ChatPresenter {
    fun checkmessage(page: ChatPage, chatModule: ChatModule, sendnick:String){
        if(chatModule.text!=null){
            Chat().sendmessage(page,chatModule,sendnick)
        }
    }
}