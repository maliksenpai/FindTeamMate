package com.e.findteammate.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.findteammate.Model.Firebase.Project
import com.e.findteammate.R
import com.sarveshathawale.kotlintoasts.shortToast

class ProjectPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_page)
        var subject = intent.getStringExtra("subject")
        Project().getdetails(subject,this)
    }
    fun errors(code:Int){
        if(code==1){
            shortToast { "Baglanti hatası" }
        }else{
            shortToast { "Hata" }
        }
    }
}
