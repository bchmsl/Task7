package com.example.task7.extensions

import android.graphics.Color

fun String.toMinutes(): String {
    val minutes = this.toLong() / 60
    return "$minutes Min"
}

fun String?.toColor(): Int {
    return Color.parseColor("#$this")
}