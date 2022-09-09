package com.example.task7.extensions

fun String.toMinutes(): String {
    val minutes = this.toLong() / 60
    return "$minutes Min"
}