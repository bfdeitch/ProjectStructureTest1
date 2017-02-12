package com.teamtreehouse.projectstructuretest1

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*


class MyAdapter(context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
  val list = Array(100, {it}).toMutableList()
  var toggle = true
  val listItemConstraints = ConstraintSet()
  val zoomedItemConstraints = ConstraintSet()

  init {
    listItemConstraints.clone(context, R.layout.recycler_item)
    zoomedItemConstraints.clone(context, R.layout.detail_view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.update(list[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
    return ViewHolder(view as ConstraintLayout)
  }

  override fun getItemCount() = list.size

  inner class ViewHolder(val view: ConstraintLayout) : RecyclerView.ViewHolder(view) {
    val textView = view.find<TextView>(R.id.textView)
    var top = 0

    fun update(i: Int) {
      textView.text = i.toString()

      view.onClick {
        TransitionManager.beginDelayedTransition(view)
        val layoutManager = (view.parent as RecyclerView).layoutManager as LinearLayoutManager
        if (toggle) {
          top = view.top
          zoomedItemConstraints.applyTo(view)
          layoutManager.scrollToPositionWithOffset(i, 0)
          view.layoutParams.height = matchParent
        } else {
          listItemConstraints.applyTo(view)
          layoutManager.scrollToPositionWithOffset(i, top)
          view.layoutParams.height = view.dip(100)
        }
        toggle = !toggle
      }
    }
  }
}