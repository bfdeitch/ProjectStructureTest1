package com.teamtreehouse.projectstructuretest1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    frameLayout {
      lparams(width = matchParent, height = matchParent)
      addView(recyclerView)
    }
  }

  override fun onDestroy() {
    recyclerView?.let {
      if (it.parent != null)
        (it.parent as ViewGroup).removeView(it)
    }
    super.onDestroy()
  }
}
