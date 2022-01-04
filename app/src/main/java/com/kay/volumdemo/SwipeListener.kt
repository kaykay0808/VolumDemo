package com.kay.volumdemo

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class SwipeListener(ctx: Context) : View.OnTouchListener {

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            scrollVolume(distanceY)
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

    }

    open fun scrollVolume(scroll: Float) {

    }
}