package com.e.findteammate.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.findteammate.Model.Firebase.EditTalent
import com.e.findteammate.R

class EditTalentPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_talent_page)
        supportActionBar?.title = "Yetenekleri düzenle"
        EditTalent().update(this)
    }
}
