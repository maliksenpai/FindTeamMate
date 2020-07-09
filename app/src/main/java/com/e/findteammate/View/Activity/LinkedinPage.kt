package com.e.findteammate.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.e.findteammate.Presenter.LinkedinPresenter
import com.e.findteammate.R
import com.sarveshathawale.kotlintoasts.shortToast

class LinkedinPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linkedin_page)
        findViewById<Button>(R.id.linkedinexitbutton).setOnClickListener { startActivity(Intent(this,
            MainPage::class.java)) }
        findViewById<Button>(R.id.linkedinaddbutton).setOnClickListener { LinkedinPresenter().checktext(this,findViewById<EditText>(R.id.linkedinedittext).text.toString(),findViewById<EditText>(R.id.linkedininfo).text.toString()) }
    }

    fun errors(code:Int){
        if(code==1){
            shortToast { "Linkedin alanını bos bırakmayın" }
        }else if(code==2){
            shortToast { "Bilgi alanını bos bırakmayın" }
        }
        else{
            shortToast { "Hata" }
        }
    }
}
