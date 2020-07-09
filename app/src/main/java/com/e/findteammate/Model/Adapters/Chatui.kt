package com.e.findteammate.Model.Adapters

import com.e.findteammate.Model.Module.ChatModule

class Chatui (val message:ChatModule,val messagetype:Int) {
    companion object{
        const val SEND_MESSAGE = 0
        const val RECIEVE_MESSAGE = 1
    }

}