package com.e.findteammate.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.ChatAdapter
import com.e.findteammate.Model.Firebase.Chat
import com.e.findteammate.Model.Module.ChatModule
import com.e.findteammate.Presenter.ChatPresenter
import com.e.findteammate.R

class ChatPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_page)
        supportActionBar?.title="asd"
        var recievenick=intent.getStringExtra("recievenick")
        var sendnick=intent.getStringExtra("sendnick")
        Chat().update(this,recievenick,sendnick)
        var recyclerview = findViewById<RecyclerView>(R.id.chatlist)
        findViewById<ImageButton>(R.id.chatsendbutton).setOnClickListener {
            ChatPresenter().checkmessage(this,ChatModule(recievenick,findViewById<EditText>(R.id.chatedittext).text.toString()),sendnick)
            findViewById<EditText>(R.id.chatedittext).setText("")
        }
    }
}
