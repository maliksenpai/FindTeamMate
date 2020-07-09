package com.e.findteammate.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.e.findteammate.Presenter.ProjectPresenter
import com.e.findteammate.R
import com.sarveshathawale.kotlintoasts.shortToast

class AddProjectPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project_page)
        //var nick = intent.getStringExtra("nick")
        findViewById<Button>(R.id.addprojectbutton).setOnClickListener {
            Log.d("proje","asd")
            ProjectPresenter().newproject(findViewById<EditText>(R.id.addprojectsubject).text.toString(),findViewById<EditText>(R.id.addprojectexplain).text.toString(),this)
        }
    }
    fun errors(code:Int){
        if(code==1){
            shortToast { "Konuyu bos bırakma" }
        }else if(code==2){
            shortToast { "Acıklamayı bos bırakma" }
        }else{

        }
    }
}
