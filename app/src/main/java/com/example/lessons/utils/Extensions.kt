package com.example.lessons.utils

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ImageView.set(uri: String) {
    Picasso.get().load(uri).into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}