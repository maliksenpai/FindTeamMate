package com.e.findteammate.View.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import com.e.findteammate.Model.Firebase.SearchProfile
import com.e.findteammate.Presenter.SearchProfilePresenter
import com.e.findteammate.R
import com.sarveshathawale.kotlintoasts.shortToast

class SearchProfilePage : AppCompatActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_profile_page)
        val nick=intent.getStringExtra("nick")
        supportActionBar?.title=nick
        SearchProfile().getDetails(nick,this)
        var button = findViewById<Button>(R.id.profileeditbutton)
        //button.layoutParams =
        //var buttonparam: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT)
        //buttonparam
        findViewById<Button>(R.id.searchmessage).setOnClickListener {
            Log.d("gelen","asd")
            SearchProfile().message(nick,this)
        }
        findViewById<Button>(R.id.searchvote).setOnClickListener { SearchProfilePresenter().votebutton(nick,this) }
    }

    fun errors(code:Int){
        if(code==1){
            shortToast { "Yüklenirken hata meydana geldi" }
        }else if(code==2){
            shortToast { "Hata meydana geldi" }
        }
    }
}
