package com.e.findteammate.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.e.findteammate.Presenter.RegisterPresenter
import com.e.findteammate.R
import com.sarveshathawale.kotlintoasts.shortToast

class RegisterPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        findViewById<Button>(R.id.registerpagebutton).setOnClickListener { RegisterPresenter().registeruser(
            findViewById<EditText>(R.id.registerpagemail).text.toString(),
            findViewById<EditText>(R.id.registerpagenick).text.toString(),
            findViewById<EditText>(R.id.registerpagepass).text.toString(),
            this
        ) }
    }
    fun errors(code:Int){
        if(code==1){
            shortToast { "Email hatalı giriş" }
        }else if(code==2){
            shortToast { "Kullanıcı adı kısa" }
        }else if(code==3){
            shortToast { "Sifre kısa" }
        }else if(code==4){
            shortToast { "Aynı mail kullanılmıs" }
        }else{
            shortToast { "Hata!" }
        }
    }
}
