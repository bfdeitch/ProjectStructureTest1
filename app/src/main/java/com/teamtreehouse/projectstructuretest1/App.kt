package com.teamtreehouse.projectstructuretest1

import android.app.Application
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

var recyclerView: RecyclerView? = null

class App : Application() {
  override fun onCreate() {
    super.onCreate()

    recyclerView = recyclerView {
      lparams(matchParent, matchParent)
      adapter = MyAdapter(context)
      layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }
  }
}