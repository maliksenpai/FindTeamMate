package com.e.findteammate.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.TalentAdapter
import com.e.findteammate.Presenter.TalentPresenter
import com.e.findteammate.R
import com.sarveshathawale.kotlintoasts.shortToast
import kotlinx.android.synthetic.main.recycler_view_item.*

class TalentPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talent_page)
        var presenter=TalentPresenter()
        var list:MutableList<String> = arrayListOf()
        var recyclerview=findViewById<RecyclerView>(R.id.talentlist)
        findViewById<Button>(R.id.talentadd).setOnClickListener {
            //TalentAdapter(list).cleardata()
            var talent=presenter.addTalent(findViewById<EditText>(R.id.talenttext).text.toString(), this,list)
            recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            supportActionBar?.title="Yeteneklerini ekle"
            recyclerview.layoutManager=LinearLayoutManager(this)
            recyclerview.adapter=TalentAdapter(list)
            Log.d("gelen",list.toString()+"zxc")
        }
        findViewById<Button>(R.id.talentfinishbutton).setOnClickListener {
            presenter.sendTalent(list,this)
        }
    }
    fun errors(code:Int){
        if(code==1) {
            shortToast { "En az 2 yetenek eklemelisiniz" }
        }else if(code==2){
            shortToast { "Listen boş" }
        }else{
            shortToast { "Hata" }
        }
    }
}
