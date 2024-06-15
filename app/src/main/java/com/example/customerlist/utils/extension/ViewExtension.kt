package com.example.customerlist.utils.extension

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.customerlist.R

fun AppCompatActivity.setStatusBarDrawable()
{
    val background: Drawable? = ContextCompat.getDrawable(this, R.drawable.status_bar_bg)
    window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    window.setBackgroundDrawable(background)
}