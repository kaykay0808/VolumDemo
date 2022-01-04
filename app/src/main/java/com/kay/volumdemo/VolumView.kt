package com.kay.volumdemo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import kotlin.math.roundToInt

class VolumView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var numberOfLines: Int
    private var volumeLevel: Int

    @ColorInt
    private val color: Int
    private val sideMargin = resources.getDimensionPixelSize(R.dimen.volum_side_margin)
    private val betweenMargin = resources.getDimensionPixelSize(R.dimen.volum_between_margin)
    private val volumeBarHeight = resources.getDimensionPixelSize(R.dimen.volum_heigt)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.volumView,
            0, 0
        ).apply {

            try {
                numberOfLines = getInteger(R.styleable.volumView_volumLines, 10)
                volumeLevel = getInteger(R.styleable.volumView_volumLevel, 10)
                color = getColor(R.styleable.volumView_volumColor, Color.CYAN)
            } finally {
                recycle()
            }
        }
        addChildren()
        setOnTouchListener(object : SwipeListener(context) {

            override fun scrollVolume(scroll: Float) {
                val scrollInt = (scroll/10).roundToInt()
                var finalVolume = volumeLevel + scrollInt
                if (finalVolume < 0) {
                    finalVolume = 0
                } else if (finalVolume > 100) {
                    finalVolume = 100
                }
                updateVolumeLevel(finalVolume)
            }
        })
    }

    private fun addChildren() {
        val firstColorIndex = numberOfLines - (numberOfLines * (volumeLevel/100f)).roundToInt()
        for (i in 0 until numberOfLines) {
            if (i < firstColorIndex){
                addView(createRectangle(Color.GRAY))
            } else {
                addView(createRectangle(color))
            }

        }
        val volumeInfo = TextView(context)
        volumeInfo.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        volumeInfo.text = resources.getString(R.string.volume_info, volumeLevel)
        volumeInfo.gravity = Gravity.CENTER
        addView(volumeInfo)

    }

    private fun createRectangle(@ColorInt color: Int): View {
        val view = View(context)
        view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, volumeBarHeight)
        view.setBackgroundColor(color)
        val params = view.layoutParams as MarginLayoutParams
        params.setMargins(sideMargin, betweenMargin, sideMargin, betweenMargin)
        return view
    }

    fun updateNumberOfLines(lines: Int) {
        numberOfLines = lines
        removeAllViewsInLayout()
        addChildren()
        invalidate()
    }

    fun updateVolumeLevel(volume: Int) {
        volumeLevel = volume
        removeAllViewsInLayout()
        addChildren()
        invalidate()
    }

}