package com.e.findteammate.View.Activity

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Firebase.Main
import com.e.findteammate.Model.Firebase.Profile
import com.e.findteammate.Presenter.MainPresenter
import com.e.findteammate.Presenter.ProjectPresenter
import com.e.findteammate.R
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sarveshathawale.kotlintoasts.shortToast
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        //main menu kısmı
        Main().checkuser(this)
        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //createnotification()
        Main().listallusers(this)
        supportActionBar?.title="Find Teammate"
        var recyclerviewmain=findViewById<RecyclerView>(R.id.mainpage_list)
        recyclerviewmain.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerviewmain.layoutManager= LinearLayoutManager(this)
        findViewById<EditText>(R.id.mainpage_text).addTextChangedListener {
            Log.d("gelen7",it.toString())
            MainPresenter().checktext(it.toString(),this)
        }
        val navigation = findViewById<BottomNavigationView>(R.id.mainnavigation)
        navigation.selectedItemId = R.id.navigationmain
        var navigationlistener = BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationmain -> {
                    findViewById<RelativeLayout>(R.id.mainlayout).visibility = View.VISIBLE
                    findViewById<RelativeLayout>(R.id.profilelayout).visibility = View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.projectslayout).visibility = View.INVISIBLE
                    supportActionBar?.title="Main Menu"
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigationprofile -> {
                    findViewById<RelativeLayout>(R.id.mainlayout).visibility = View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.profilelayout).visibility = View.VISIBLE
                    findViewById<RelativeLayout>(R.id.projectslayout).visibility = View.INVISIBLE
                    Profile().profilebar(this)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigationprojects -> {
                    findViewById<RelativeLayout>(R.id.mainlayout).visibility = View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.profilelayout).visibility = View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.projectslayout).visibility = View.VISIBLE
                    supportActionBar?.title="Projects"
                    return@OnNavigationItemSelectedListener true
                }

                else -> {
                    return@OnNavigationItemSelectedListener true
                }
                }
            }
        navigation.setOnNavigationItemSelectedListener(navigationlistener)

        /// profil kısmı
        var recyclerviewprofile=findViewById<RecyclerView>(R.id.profiletalentlist)
        recyclerviewprofile.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerviewprofile.layoutManager= LinearLayoutManager(this)
        Profile().getDetail(this)
        findViewById<Button>(R.id.profileexitbutton).setOnClickListener { Profile().exituser(this) }
        findViewById<Button>(R.id.profilechatbutton).setOnClickListener { startActivity(Intent(this,ChatListPage::class.java)) }


        //project kısmı
        findViewById<EditText>(R.id.projecttext).addTextChangedListener {
            ProjectPresenter().checktext(it.toString(),this)
        }
        var recyclerviewproject = findViewById<RecyclerView>(R.id.projectlist)
        recyclerviewprofile.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerviewproject.layoutManager = LinearLayoutManager(this)
        findViewById<ImageButton>(R.id.projectbutton).setOnClickListener { startActivity(Intent(this,AddProjectPage::class.java)) }
        Main().getmessages(this,manager)
        }
    fun errors(code:Int){
        if(code==1){
            shortToast { "Listelenme sırasında hata gerçeklesti" }
        }
    }


    fun createnotification(page:MainPage,manager: NotificationManager){
        var builder = NotificationCompat.Builder(page)
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setContentTitle("Find Team Mate")
            .setContentText("Yeni bir mesajınız var")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        //var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0,builder.build())
    }
}
