package com.e.findteammate.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.findteammate.Model.Firebase.ChatList
import com.e.findteammate.R

class ChatListPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list_page)
        ChatList().list(this)
    }
}
