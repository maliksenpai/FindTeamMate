package com.e.findteammate.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.e.findteammate.Presenter.LoginPresenter
import com.e.findteammate.R
import com.sarveshathawale.kotlintoasts.shortToast

class LoginPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        findViewById<Button>(R.id.loginpagebutton).setOnClickListener { LoginPresenter().checklogin(
            findViewById<EditText>(R.id.loginpagemail).text.toString(),
            findViewById<EditText>(R.id.loginpagepass).text.toString(),
            this) }
        findViewById<TextView>(R.id.loginpageregister).setOnClickListener { startActivity(Intent(this,
            RegisterPage::class.java)) }
    }
    fun errors(code:Int){
        if(code==1){
            shortToast { "Email hatalı" }
        }else if(code==2){
            shortToast { "Sifre kısa" }
        }else if(code==3){
            shortToast { "Bilgileriniz yanlis" }
        }else{
            shortToast { "Hata" }
        }
    }
}
