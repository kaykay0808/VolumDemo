package com.kay.volumdemo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt

class VolumView (context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    var numberOflines:Int
    var volumLevel:Int
    @ColorInt val color: Int
    val sideMargin = resources.getDimensionPixelSize(R.dimen.volum_side_margin)
    val betweenMargin = resources.getDimensionPixelSize(R.dimen.volum_between_margin)
    val volumBarHeight = resources.getDimensionPixelSize(R.dimen.volum_heigt)

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
        addChildren()
    }

    fun addChildren () {
        for (i in 1..numberOflines){
            addView(createRectangle())
        }
        val volumeInfo = TextView(context)
        volumeInfo.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        volumeInfo.text = resources.getString(R.string.volume_info, volumLevel)
        volumeInfo.gravity = Gravity.CENTER
        addView(volumeInfo)

    }

    fun createRectangle():View {
        val view = View(context)
        view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, volumBarHeight)
        view.setBackgroundColor(color)
        val params = view.layoutParams as MarginLayoutParams
        params.setMargins(sideMargin,betweenMargin,sideMargin,betweenMargin)
        return view
    }

    fun updateNumberOfLines(lines:Int){
        numberOflines = lines
        removeAllViewsInLayout()
        addChildren()
        invalidate()
    }
    fun updateVolumeLevel(volume: Int){
        volumLevel = volume
        removeAllViewsInLayout()
        addChildren()
        invalidate()
    }
}