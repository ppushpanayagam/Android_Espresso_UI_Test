package com.example.testapplication.ui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.INVALID_POINTER_ID
import android.view.ScaleGestureDetector
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.testapplication.R


class PinchZoomImageView(
    context: Context, attrs: AttributeSet?
) : RelativeLayout(context, attrs) {


    private val imageView: ImageView

    init {
        inflate(context, R.layout.view_image, this)
        imageView = findViewById(R.id.imageView)
    }

    private var scale = 1f

    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scale *= detector.scaleFactor

            // Don't let the object get too small or too large.
            scale = Math.max(1f, Math.min(scale, 5.0f))

            Log.d("PinchZoomImageView", "scale = $scale")
            imageView.scaleX = scale
            imageView.scaleY = scale
            imageView.invalidate()
            return true
        }
    }
    private val scaleDetector = ScaleGestureDetector(context, scaleListener)
    private var lastTouchX: Float = 0F
    private var lastTouchY: Float = 0F
    private var activePointerId: Int = 0
    private var posX: Float = 0F
    private var posY: Float = 0F

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        // Let the ScaleGestureDetector inspect all events.
        scaleDetector.onTouchEvent(ev)

        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                ev.actionIndex.also { pointerIndex ->
                    // Remember where you start for dragging.
                    lastTouchX = ev.getX(pointerIndex)
                    lastTouchY = ev.getY(pointerIndex)
                }

                // Save the ID of this pointer for dragging.
                activePointerId = ev.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                // Find the index of the active pointer and fetch its position.
                val (x: Float, y: Float) =
                    ev.findPointerIndex(activePointerId).let { pointerIndex ->
                        // Calculate the distance moved.
                        ev.getX(pointerIndex) to ev.getY(pointerIndex)
                    }

                posX += (x - lastTouchX).coerceIn(-10f, 10f)
                posY += (y - lastTouchY).coerceIn(-10f, 10f)

                imageView.x = posX
                imageView.y = posY
                invalidate()

                // Remember this touch position for the next move event.
                lastTouchX = x
                lastTouchY = y
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                activePointerId = INVALID_POINTER_ID
            }
            MotionEvent.ACTION_POINTER_UP -> {
                ev.actionIndex.also { pointerIndex ->
                    ev.getPointerId(pointerIndex)
                        .takeIf { it == activePointerId }
                        ?.run {
                            // This is the active pointer going up. Choose a new
                            // active pointer and adjust it accordingly.
                            val newPointerIndex = if (pointerIndex == 0) 1 else 0
                            lastTouchX = ev.getX(newPointerIndex)
                            lastTouchY = ev.getY(newPointerIndex)
                            activePointerId = ev.getPointerId(newPointerIndex)
                        }
                }
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            save()
            scale(scale, scale)
            // onDraw() code goes here.
            restore()
        }
    }

}