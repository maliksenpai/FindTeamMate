package com.e.findteammate.Model.Firebase

import android.content.Intent
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.findteammate.Model.Adapters.ProjectAdapter
import com.e.findteammate.Model.Module.ProjectModule
import com.e.findteammate.R
import com.e.findteammate.View.Activity.AddProjectPage
import com.e.findteammate.View.Activity.MainPage
import com.e.findteammate.View.Activity.ProjectPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Project {
    fun listallprojects(main: MainPage){
        var database = FirebaseDatabase.getInstance().reference.child("projeler")
        var recyclerview = main.findViewById<RecyclerView>(R.id.projectlist)
        var list:MutableList<ProjectModule> = mutableListOf()
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0 : DataSnapshot){
                p0.children.forEach{
                    Log.d("projectgelen",it.child("konu").getValue().toString())
                    list.add(ProjectModule(it.child("konu").getValue().toString(),it.child("nick").getValue().toString()))
                }
                recyclerview.adapter = ProjectAdapter(list)
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun listprojects(search:String,main:MainPage){
        var database = FirebaseDatabase.getInstance().reference.child("projeler")
        var recyclerview = main.findViewById<RecyclerView>(R.id.projectlist)
        var list:MutableList<ProjectModule> = mutableListOf()
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0 : DataSnapshot){
                p0.children.forEach{
                    var subject = it.child("subject").getValue().toString()
                    Log.d("gelenproje2",subject)
                    if(subject.contains(search)) {
                        list.add(
                            ProjectModule(
                                it.child("konu").getValue().toString(),
                                it.child("nick").getValue().toString()
                            )
                        )
                    }}
                Log.d("gelenproje",list.toString())
                recyclerview.adapter = ProjectAdapter(list)
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }

    fun addproject(subject:String,explain:String,page:AddProjectPage){
        var database = FirebaseDatabase.getInstance().reference.child("projeler").push()
        var accounts= FirebaseDatabase.getInstance().reference.child("kullanicilar")
        database.child("konu").setValue(subject)
        database.child("aciklama").setValue(explain)
        Log.d("proje",subject+"-"+explain+"-"+page.toString())
        val mail = FirebaseAuth.getInstance().currentUser?.email
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                AddProjectPage().errors(3)
                Log.d("proje",p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("proje","zxc")
                p0.children.forEach {
                    Log.d("proje",it.child("mail").getValue().toString())
                    if(it.child("mail").getValue().toString().equals(mail)){
                        database.child("konu").setValue(subject)
                        database.child("aciklama").setValue(explain)
                        database.child("nick").setValue(it.child("nick").getValue().toString())
                        page.startActivity(Intent(page,MainPage::class.java))
                    }
                }
            }
        }
        accounts.addListenerForSingleValueEvent(listener)
    }

    fun getdetails(subject:String,page:ProjectPage){
        var database = FirebaseDatabase.getInstance().reference.child("projeler")
        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                page.errors(1)
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("konu").getValue().toString().equals(subject)){
                        page.findViewById<TextView>(R.id.projectsubject).setText("Project Name \n"+subject)
                        page.findViewById<TextView>(R.id.projectnick).setText("Project owner \n"+it.child("nick").getValue().toString())
                        page.findViewById<TextView>(R.id.projectinfo).setText("Project Description \n"+it.child("aciklama").getValue().toString())
                    }
                }
            }
        }
        database.addListenerForSingleValueEvent(listener)
    }

}
