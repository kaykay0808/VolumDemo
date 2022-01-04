package com.kay.volumdemo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt

class VolumView (context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    var numberOflines:Int
    var volumLevel:Int
    @ColorInt val color: Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.volumView,
            0, 0).apply {

            try {
                numberOflines = getInteger(R.styleable.volumView_volumLines, 10)
                volumLevel = getInteger(R.styleable.volumView_volumLevel, 10)
                color = getColor(R.styleable.volumView_volumColor, Color.CYAN)
            } finally {
                recycle()
            }
        }
        addLines()
    }

    fun addLines () {
        for (i in 1..numberOflines){
            addView(createRectangle())
        }
    }

    fun createRectangle():View {
        val view = View(context)
        view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 20)
        view.setBackgroundColor(color)
        return view
    }
}