package com.e.findteammate.Presenter

import android.content.Intent
import android.util.Log
import com.e.findteammate.Model.Firebase.Project
import com.e.findteammate.View.Activity.AddProjectPage
import com.e.findteammate.View.Activity.MainPage

class ProjectPresenter {
    fun checktext(search:String,page:MainPage){
        if(search.length==0){
            Project().listallprojects(page)
        }else{
            Project().listprojects(search,page)
        }
    }
    fun newproject(subject:String,explain:String,page:AddProjectPage){
        Log.d("proje",subject+"-"+explain)
        if(subject.isNullOrEmpty()){
            page.errors(1)
            Log.d("proje","hata1")
        }else if(explain.isNullOrEmpty()){
            Log.d("proje","hata2")
            page.errors(2)
        }else{
            Log.d("proje","oldu")
            Project().addproject(subject,explain,page)
        }
    }
}