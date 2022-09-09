package com.example.task7.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String){
    Glide.with(this).load(url).into(this)
}