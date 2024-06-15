package com.example.customerlist.utils

import android.graphics.Color

object Common {
     fun getColour(pos:Int):Int{
        val color= when(pos%3)
        {
            0 -> Color.RED
            1 -> Color.GREEN
            2 -> Color.BLUE
            else -> Color.WHITE
        }
        return color
    }
}